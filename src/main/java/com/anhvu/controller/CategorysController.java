/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.Pagination;
import com.anhvu.dto.ProductDto;
import com.anhvu.model.Categorys;
import com.anhvu.model.Users;
import com.anhvu.repository.CategoryRepository;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.PaginationService;

/**
 *
 * @author Admin
 */
@Controller
public class CategorysController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategorysController.class);

    private final int limit = 6;

    @Autowired
    IHomeService homeService;

    @Autowired
    PaginationService paginationService;

    @Autowired
    IProductService productService;
    
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/category/{id}")
    public ModelAndView category(@PathVariable("id") @Valid Integer id) {
        
        return getCategoryView(id, 1);
    }

    @RequestMapping("/category/{id}/{numberPage}")
    public ModelAndView categoryByPagination(@PathVariable("id") @Valid Integer id, 
    		@PathVariable("numberPage") Integer numberPage, @ModelAttribute("user") Users user,
    		HttpSession session) {
    	
    	session.removeAttribute("status");
        return getCategoryView(id, numberPage);
    }
    
    private ModelAndView getCategoryView(Integer id, Integer numberPage) {
        ModelAndView view = new ModelAndView("/user/products");
        
        try {
	        Categorys category = homeService.getCategoryById(id);
	        List<ProductDto> listPByCa = productService.getProductsByCategory(category);
	        int totalData = listPByCa.size();
       
        if (totalData < 1) {
            view.addObject("status", "No products");
            return view;
        }

        Pagination infoPagination = paginationService.getInfoPagination(totalData, numberPage, limit);
        List<ProductDto> listPByPagination = productService.getListPByPanigation(category, infoPagination.getStart(), infoPagination.getEnd());
        
        getViewDetailCategory(view, id, infoPagination, listPByPagination, listPByCa);
        
        } catch (IllegalStateException e) {
			logger.error("Category with id not found", e);
			view.addObject("status", "No products");
		} catch (Exception e) {
			logger.error("Load Category with id not found", e);
			view.addObject("status", "Load Category unsuccessful");
		}
        
        return view;
    }
    
    private void getViewDetailCategory(ModelAndView view, Integer id, Pagination infoPagination, 
    		List<ProductDto> listPByPagination, List<ProductDto> listPByCa) {
    	
    	view.addObject("pLatest", productService.getProductsNewLastest())
	        .addObject("categorys", homeService.getListCategorys())
	        .addObject("menus", homeService.getListMenus())
	        .addObject("idCategory", id)
	        .addObject("pagination", infoPagination)
	        .addObject("listPByPagination", listPByPagination)
	        .addObject("listPByCa", listPByCa);
    }

}
