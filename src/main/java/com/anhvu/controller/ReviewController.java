/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.model.ProductReview;
import com.anhvu.model.Users;
import com.anhvu.security.UsersDetail;
import com.anhvu.security.oauth.CustomerOAuth2User;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.IUserService;

/**
 *
 * @author Admin
 */
@RestController
@Validated
public class ReviewController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	IHomeService homeService;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@PostMapping(value = "/submit_review")
	public ModelAndView addReview(@ModelAttribute("product_review") @Valid ProductReview product_review,
			@Param("file") MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException {

		ModelAndView view = new ModelAndView();

		if (!product_review.getEvaluate().isEmpty() && !product_review.getComment().isEmpty()
				&& !product_review.getName().isEmpty()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication == null || !authentication.isAuthenticated()) {
				view.addObject("status", "Please login!");
				view.setViewName("redirect:" + request.getHeader("Referer"));
				return view;
			}

			Object userAuth = authentication.getPrincipal();
			if (authentication.getPrincipal() instanceof OAuth2User) {
				CustomerOAuth2User auth2User = new CustomerOAuth2User((OAuth2User) userAuth);
				Users users = userService.getUserByEmail(auth2User.getEmail());
				product_review.setUser(users);
			} else if (authentication.getPrincipal() instanceof UsersDetail) {
				Users users = ((UsersDetail) userAuth).getUser();
				product_review.setUser(users);
			} else {
				session.setAttribute("status", "User type not supported!");
				view.setViewName("redirect:" + request.getHeader("Referer"));
				return view;
			}

			if (!file.isEmpty()) {
				String name = productService.saveFile(file);
				product_review.setProductImage(name);
			}

			try {
				productService.addReview(product_review);
				session.removeAttribute("status");
			} catch (Exception e) {
				logger.error("Add review product error", e);
				session.setAttribute("status", "Add review product unsuccessful!");
			}
		} else {
			session.setAttribute("status", "Vui lòng nhập thông tin!");
		}

		view.setViewName("redirect:" + request.getHeader("Referer"));
		return view;
	}

}
