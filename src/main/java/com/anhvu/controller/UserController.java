package com.anhvu.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.BillsDto;
import com.anhvu.model.Users;
import com.anhvu.service.IBillService;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IUserService;
import com.anhvu.utils.URL;

import net.bytebuddy.utility.RandomString;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService service;

	@Autowired
	IHomeService homeService;

	@Autowired
	IBillService billService;

	@Autowired
	JavaMailSender javaMailSender;

	
	@GetMapping("/forgot_password")
	public ModelAndView forgot_password() {
		ModelAndView view = new ModelAndView("user/account/forgot_password");
		
		view.addObject("pageTitle", "Fogot Password")
			.addObject("message", "Nhập email của bạn và chúng tôi sẽ gửi link thay đổi mật khẩu đến bạn");
		
		return view;
	}

	@GetMapping(value = "/reset_password")
	public ModelAndView formForget(@RequestParam("token") String token) {
		ModelAndView view = new ModelAndView("user/account/forgot_password_form");
		
		try {
			service.getUserByAccessToken(token);
			view.addObject("Title", "Forgot Password")
				.addObject("token", token);
			return view;
		} catch (IllegalStateException e) {
			logger.error("Token invalid",e);
			view.addObject("Title", "Forgot Password: Invalid or expired token");
			return view;
		}
	}

	@PostMapping("/form_forgot_password")
	public ModelAndView updatePassword(@RequestParam("password") String pass, @RequestParam("token") String token) {
		ModelAndView view = new ModelAndView("user/account/forgot_password_form");
		
		try {	
			Users users = service.getUserByAccessToken(token);
			service.updateUserPassword(pass, users);
			
			view.addObject("Title", "Reset password successfull!");
			return view;
		} catch (IllegalStateException e) {
			logger.error("Token invalid", e);
			view.addObject("Title", "Forgot Password: Invalid or expired token");
			return view;
		}

	}

	@PostMapping("/forgot_password")
	public ModelAndView forgot_passwordToken(@RequestParam("email") String email, 
			HttpServletRequest request) throws BindException {
		ModelAndView view = new ModelAndView("user/account/forgot_password");
		
		if (StringUtils.isNotBlank(email)) {
			String token = RandomString.make(45);
			service.updateUserResetPasswordToken(email, token);

			String url = URL.getURL(request) + "/reset_password?token=" + token;
			try {
				sendMail(email, url);
				view.addObject("pageTitle", "Fogot Password")
					.addObject("message", "Chúng tôi đã gửi link đến email của bạn, vui lòng kiểm tra");
			} catch (UnsupportedEncodingException e) {
				view.addObject("pageStatus", e.getMessage());
			} catch (MessagingException e) {
				view.addObject("pageStatus", e.getMessage());
			}
			
		} else {
			throw new BindException("Vui long nhap du va dung thong tin", email);
		}

		return view;
	}

	@Async
	public void sendMail(String toEmail, String linkForgotPassword) 
			throws UnsupportedEncodingException, MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("huynhanhvu02092002@gmail.com", "Diamond Shop Support");
		helper.setTo(toEmail);
		helper.setSubject("Here is the link reset your password");
		String content = "<p>Hello, You have requested to reset your password. </p>" + "<p><b><a href=\""
				+ linkForgotPassword + "\">Click on this link</a></b> to change your password</p>";
		helper.setText(content, true);

		javaMailSender.send(message);

	}

	@GetMapping("/history")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ModelAndView orderHistory() {
		ModelAndView view = new ModelAndView("/user/history");
		
		List<BillsDto> history = billService.getListOrdersHistory();
		if (history.isEmpty()) {
			view.addObject("notification", "Chưa có dữ liệu đặt hàng!");
		}

		view.addObject("menus", homeService.getListMenus())
			.addObject("history", history);

		return view;
	}

	@GetMapping("/pdf/{idOrder}")
	public ResponseEntity<byte[]> exportOrderToPdf(@PathVariable("idOrder") int idOrder) {
		byte[] pdfByte = billService.generateOrderPdf(idOrder);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "Order_" + idOrder + ".pdf");

		return ResponseEntity.ok().headers(headers).body(pdfByte);
	}
}
