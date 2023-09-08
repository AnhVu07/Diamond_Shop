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
import com.anhvu.spring.entity.ProductReview;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@RestController
public class DetailController {

    @Autowired
    HomeServiceImp hs;

    @Autowired
    NewProductDao newProductDao;

    @GetMapping("/detail/{pid}")
    public ModelAndView detail(@PathVariable("pid") String pid, @ModelAttribute("user") Users user, @ModelAttribute("product_review") ProductReview product_review) {
        ModelAndView m = new ModelAndView("/user/detail");
        ProductDto PDetail = new ProductDto();
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        List<ProductReview> list = new ArrayList<ProductReview>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        PDetail = hs.getProductsById(pid);
        list = newProductDao.getProductsReview(pid);
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("detail", PDetail);
        m.addObject("listReview", list);
        return m;
    }
    
    

    
}
