/*
02 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.model.Users;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.IUserService;

/**
 *
 * @author Admin
 */
@RestController
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	IHomeService homeService;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView view = new ModelAndView("/user/account/register");
		
		view.addObject("pLatest", productService.getProductsNewLastest())
        	.addObject("categorys", homeService.getListCategorys())
        	.addObject("menus", homeService.getListMenus())
			.addObject("user", new Users());
		
		return view;
	}

	@PostMapping(value = "/dang-ky")
	public ModelAndView createAcc(@RequestBody @ModelAttribute("user") @Valid Users user, 
			HttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView();
		try {
			userService.addUser(user);
			session.setAttribute("status1", "Sign up success!");
		} catch (IllegalStateException e) {
			logger.error("Sign up error", e);
			session.setAttribute("status1", "Sign up unsuccessful!");
		}
		view.setViewName("redirect:"+request.getHeader("Referer"));
		
		return view;
	}

}
