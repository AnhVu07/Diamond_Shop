/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.anhvu.dto.Pagination;
import com.anhvu.dto.ProductDto;
import com.anhvu.model.Contact;
import com.anhvu.security.UsersDetail;
import com.anhvu.service.IBillService;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.PaginationService;



@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final int limit = 9;

    @Autowired
    IHomeService homeService;

    @Autowired
    IProductService productService;

    @Autowired
    PaginationService paginationService;
    
    @Autowired
    IBillService billService;

    @Autowired
    JavaMailSender javaMailSender;

    
    @GetMapping(value = {"/home","/"})
    public ModelAndView index(HttpSession session) {
        session.removeAttribute("status");

        return getViewHome(1);
    }

    @GetMapping("/getPByPaginationHome/{currenPage}")
    public ModelAndView getPByPaginationHome(@PathVariable("currenPage") @Valid int currenPage) {
        
        return getViewHome(currenPage);
    }
    
    private ModelAndView getViewHome(int currenPage) {
    	ModelAndView view = new ModelAndView("/user/index");
    	 
    	try {
	        List<ProductDto> allProducts = productService.getListProductDtos();
	        int totalProducts = allProducts.size();
	        Pagination pagination = paginationService.getInfoPagination(totalProducts, currenPage, limit);
	        List<ProductDto> paginatedProducts = productService.getListPByPanigationHome(pagination.getStart(), pagination.getEnd());
	
	        addUserDisplayName(view);
	        addHomePageData(view, pagination, paginatedProducts);
    	} catch (Exception e) {
			logger.error("Load data page home error!", e);
			view.addObject("status", "Load page Home failed!");
		}
    	return view;
    }
    
    private void addUserDisplayName(ModelAndView view) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            String displayName = ((UsersDetail) auth.getPrincipal()).getDisplayName();
            view.addObject("displayName", displayName);
        }
    }
    
    private void addHomePageData(ModelAndView view, Pagination pagination, List<ProductDto> paginatedProducts ) {
    	view.addObject("slides", homeService.getListSlides())
	        .addObject("categorys", homeService.getListCategorys())
	        .addObject("menus", homeService.getListMenus())
	        .addObject("products", productService.getListProductDtos())
	        .addObject("newproducts", productService.getListNewProducts())
	        .addObject("pLatest", productService.getProductsNewLastest())
	        .addObject("pagination", pagination)
	        .addObject("listPByPagination", paginatedProducts);
    }

    @GetMapping(value = {"/about_us"})
    public ModelAndView about_us() {
        ModelAndView view = new ModelAndView("/user/about_us");

        view.addObject("menus", homeService.getListMenus());
        
        return view;
    }

    @GetMapping(value = {"/contact"})
    public ModelAndView contact(@ModelAttribute("contact") Contact contact) {
        ModelAndView view = new ModelAndView("/user/contact");
        
        view.addObject("menus", homeService.getListMenus());
        
        return view;
    }

    @PostMapping(value = {"/sendMail"})
    public ModelAndView sendMail(@RequestBody @ModelAttribute("contact") @Valid Contact contact) {
        ModelAndView view = new ModelAndView("/user/contact");
        
            if (contact!=null &&!contact.toString().isEmpty()) {
            	try {
	                homeService.sendMail(contact.getEmail(), "Thank you for emailing us(Diamond Shop)", "We have received your email and will respond soon!");
	                homeService.addContact(contact);
            	} catch (Exception e) {
                    logger.error("Email sending failed!!!", e);
                    view.addObject("mess", "send mail unsuccessful!");
                }
            }
        
        view.setViewName("redirect:contact");
        return view;
    }

    @GetMapping(value = {"/403"})
    public ModelAndView Page403() {
        ModelAndView view = new ModelAndView("/user/403");
        return view;
    }
    
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
    	ModelAndView view = new ModelAndView("/user/login");
        String error = (String) request.getAttribute("error");
        if (error != null) {
            view.addObject("error", error);
        }
      
        return view;
    }
    
   

}
