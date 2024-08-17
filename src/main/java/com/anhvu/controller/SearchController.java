/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.ProductDto;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;

/**
 *
 * @author Admin
 */
@Controller
public class SearchController {

	@Autowired
	IHomeService homeService;

	@Autowired
	IProductService productService;

	@GetMapping(value = "/search/")
	public ModelAndView search(@RequestParam("name") @Valid String name, HttpSession session) {
		ModelAndView view = new ModelAndView("/user/search");
		List<ProductDto> listProductSearch = new ArrayList<ProductDto>();

		if (StringUtils.isNotBlank(name)) {
			listProductSearch = productService.getProductsBySearch(name.trim());

			if (listProductSearch.isEmpty()) {
				session.setAttribute("status", "Not found!");
			} else {
				session.removeAttribute("status");
			}
		} else {
			session.setAttribute("status", "Please enter search!");
		}

		view.addObject("pLatest", productService.getProductsNewLastest())
			.addObject("categorys", homeService.getListCategorys())
			.addObject("menus", homeService.getListMenus())
			.addObject("key", name)
			.addObject("search", listProductSearch);

		return view;
	}

}
