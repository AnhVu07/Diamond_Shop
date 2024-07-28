/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.ProductDto;
import com.anhvu.model.Categorys;
import com.anhvu.model.ProductReview;
import com.anhvu.model.Products;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;

/**
 *
 * @author Admin
 */
@RestController
public class DetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    IHomeService homeService;

    @Autowired
    IProductService productService;


    @GetMapping("/detail/{pid}")
    public ModelAndView detail(@PathVariable("pid") @Valid long pid,
    		@ModelAttribute("product_review") ProductReview product_review) {
        ModelAndView view = new ModelAndView("/user/detail");
       
        try {
	        ProductDto PDetail = productService.getProductDtoById(pid);
	        
	        Products products = productService.getProductById(pid);
	        List<ProductReview> listReview = productService.getProductsReview(products);
	        
	        Categorys idCategory = PDetail.getId_category();
	        List<ProductDto> listPByCategory = productService.getListPByPanigation(idCategory, 2, 5);
	        
	        getViewDetail(view, listPByCategory, PDetail, listReview);
        } catch (IllegalStateException e) {
        	view.addObject("status", "Load detail product with id unsuccessful!");
			logger.error("Error load detail product with id: "+pid, e);
		} catch (Exception e) {
			view.addObject("status", "Load detail product unsuccessful!");
			logger.error("Error load detail product", e);
		}
        
        return view;
    }
    
    private void getViewDetail(ModelAndView view, List<ProductDto> listPByCategory, 
    		ProductDto PDetail, List<ProductReview> listReview) {
    	
    	view.addObject("categorys", homeService.getListCategorys())
	        .addObject("menus", homeService.getListMenus())
	        .addObject("pLatest", productService.getProductsNewLastest())
	        .addObject("listPByCategory", listPByCategory)
	        .addObject("detail", PDetail)
	        .addObject("listReview", listReview);
    }
    
}
