/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.CartDto;
import com.anhvu.model.Bills;
import com.anhvu.model.Users;
import com.anhvu.security.UsersDetail;
import com.anhvu.security.oauth.CustomerOAuth2User;
import com.anhvu.service.IBillService;
import com.anhvu.service.ICartDto;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.IUserService;

/**
 *
 * @author Admin
 */
@RestController
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	ICartDto cartDtoImp;

	@Autowired
	IBillService billService;

	@Autowired
	IProductService productService;

	@Autowired
	IHomeService homeService;

	@Autowired
	IUserService userService;

	@RequestMapping("/cart/{id}/{quantity}")
	public ModelAndView addToCart(@PathVariable @Valid Long id, @PathVariable @Valid Integer quantity,
			HttpSession session, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		if (!isValidQuantity(quantity)) {
			view.addObject("status", "Please enter a number equal to or greater than 1");
			return view;
		}

		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		
		try {
			cart = cartDtoImp.addCart(id, quantity, cart);
		} catch (Exception e) {
			logger.error("Add cart failed", e);
			view.addObject("status", "Add failed!Please try again later");
		}
		updateSession(session, cart);
		view.setViewName("redirect:" + request.getHeader("Referer"));
		
		return view;
	}

	private boolean isValidQuantity(Integer quantity) {
		return quantity != null && quantity > 0 && cartDtoImp.isNumber(quantity.toString());
	}

	private boolean isValidQuantityUpdate(Integer quantity) {
		return quantity != null && cartDtoImp.isNumber(quantity.toString());
	}

	private void updateSession(HttpSession session, HashMap<Long, CartDto> cart) {
		int totalQuantity = (int) cartDtoImp.totalQuatity(cart);
		double totalPrice = cartDtoImp.totalPrice(cart);

		session.setAttribute("cart", cart);
		session.setAttribute("totalQuatity", totalQuantity);
		session.setAttribute("totalPrice", totalPrice);
		session.removeAttribute("status");
	}

	@RequestMapping("/cart")
	public ModelAndView cart() {
		ModelAndView view = new ModelAndView("/user/cart");

		view.addObject("menus", homeService.getListMenus());

		return view;
	}

	@RequestMapping("/deleteCart/{id}")
	public ModelAndView deleteCart(@PathVariable @Valid Long id, HttpSession session) {
		ModelAndView view = new ModelAndView();
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("cart");
		if (cart != null) {
			try{
				cartDtoImp.deleteCart(id, cart);
			} catch (Exception e) {
				logger.error("Delete cart failed", e);
				view.addObject("status", "Delete failed!Please try again later");
			}
			updateSession(session, cart);
		}
		view.setViewName("redirect:/cart");

		return view;
	}

	@RequestMapping(value = "/editCart/{id}/{quaty}")
	public ModelAndView editCart(@PathVariable("id") @Valid Long id, @PathVariable("quaty") @Valid Integer quantity,
			HttpSession session) {
		ModelAndView view = new ModelAndView("");

		if (!isValidQuantityUpdate(quantity)) {
			view.addObject("status", "Please enter a number equal to or greater than 1");
			return view;
		}

		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		try {
			if (quantity == 0) {
				cart = cartDtoImp.deleteCart(id, cart);
			} else {
				cart = cartDtoImp.updateCart(id, quantity, cart);
			}
		} catch (Exception e) {
			logger.error("Edit cart failed", e);
			view.addObject("status", "Edit failed!Please enter correctly");
		}

		updateSession(session, cart);
		view.setViewName("redirect:/cart");

		return view;
	}

	@GetMapping(value = "/checkout")
	public ModelAndView checkout(@ModelAttribute("bill") Bills bill,
			HttpSession session, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/user/checkout/checkout");
		
		Users users = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null || authentication.isAuthenticated()) {
			Object userAuth = authentication.getPrincipal();
			
			if (authentication.getPrincipal() instanceof OAuth2User) {
				CustomerOAuth2User auth2User = new CustomerOAuth2User((OAuth2User) userAuth);
				Users usersLogin = userService.getUserByEmail(auth2User.getEmail());
				users = userService.getUsersById(usersLogin.getId());
			} else if (authentication.getPrincipal() instanceof UsersDetail) {
				int usersId = ((UsersDetail) userAuth).getIdUser();
				users = userService.getUsersById(usersId);
			}
		}
		getViewCheckout(view, users);

		return view;
	}
	
	private void getViewCheckout(ModelAndView view, Users users) {
		view.addObject("users", users)
			.addObject("pLatest", productService.getProductsNewLastest())
			.addObject("categorys", homeService.getListCategorys())
			.addObject("menus", homeService.getListMenus());
	}

	@PostMapping(value = "/checkout")
	public ModelAndView checkoutBill(@RequestBody @ModelAttribute("bill") @Valid Bills bill,
			HttpSession session, HttpServletRequest request,
			@RequestParam("payment") Integer payment) {
		ModelAndView view = new ModelAndView();
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("cart");
		if (cart != null) {
			bill.setQuatity((int) session.getAttribute("totalQuatity"));
			bill.setTotal((double) session.getAttribute("totalPrice"));

			try {
				handlerCheckout(bill, cart, session);

				if (payment.equals(0) && payment != null) {

					view.setViewName("redirect:vn-pay");
					return view;
				} else {

					view.setViewName("redirect:home");
					return view;
				}

			} catch (Exception e) {
				session.setAttribute("status", "Thanh toán xảy ra lỗi!");
				view.setViewName("redirect:cart");
				e.printStackTrace();
				return view;
			}

		} else {
			session.setAttribute("status", "Giỏ hàng rỗng!");
			view.setViewName("redirect:cart");
			return view;
		}

	}
	
	private void handlerCheckout(Bills bill, HashMap<Long, CartDto> cart, HttpSession session) {
		billService.addBill(bill);
		billService.addBillDetail(cart);
		session.removeAttribute("status");
		session.removeAttribute("cart");
		session.removeAttribute("totalQuatity");
		session.removeAttribute("totalPrice");
	}
	
}
