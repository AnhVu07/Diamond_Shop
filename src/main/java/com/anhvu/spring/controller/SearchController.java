/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class SearchController {

    @Autowired
    HomeServiceImp homeServiceImp;

    @Autowired
    NewProductDao newProductDao;

    @GetMapping(value = "/search/")
    public ModelAndView search(@RequestParam("name") String name, @ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/search");
        List<ProductDto> PSearch = new ArrayList<ProductDto>();
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        listC = homeServiceImp.getListCategorys();
        listM = homeServiceImp.getListMenus();
        PSearch = homeServiceImp.getProductsBySearch(name);
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("key", name);
        m.addObject("search", PSearch);
        return m;
    }
}
