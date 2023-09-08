/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.dto.Pagination;
import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import com.anhvu.spring.service.PaginationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class CategorysController {

    private final int limit = 6;

    @Autowired
    HomeServiceImp hs;

    @Autowired
    PaginationService paginationService;

    @Autowired
    NewProductDao newProductDao;

    @RequestMapping("/category/{id}")
    public ModelAndView category(@PathVariable("id") String id, @ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/products");
        List<ProductDto> listPByCa = new ArrayList<ProductDto>();
        List<ProductDto> listPByPagination = new ArrayList<ProductDto>();
        Pagination infoPagination = new Pagination();
        listPByCa = hs.getProductsByCategory(id);
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        int totalData = listPByCa.size();
        infoPagination = paginationService.getInfoPagination(totalData, 1, limit);
        listPByPagination = newProductDao.getProductsByPagination(id, infoPagination.getStart(), infoPagination.getEnd());
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("idCategory", id);
        m.addObject("pagination", infoPagination);
        m.addObject("listPByPagination", listPByPagination);
        m.addObject("listPByCa", listPByCa);
        return m;
    }

    @RequestMapping("/category/{id}/{numberPage}")
    public ModelAndView categoryByPagination(@PathVariable("id") String id, @PathVariable("numberPage") String numberPage, @ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/products");
        List<ProductDto> listPByCa = new ArrayList<ProductDto>();
        List<ProductDto> listPByPagination = new ArrayList<ProductDto>();
        Pagination infoPagination = new Pagination();
        listPByCa = hs.getProductsByCategory(id);
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        int totalData = listPByCa.size();
        infoPagination = paginationService.getInfoPagination(totalData, Integer.parseInt(numberPage), limit);
        listPByPagination = newProductDao.getProductsByPagination(id, infoPagination.getStart(), infoPagination.getEnd());
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("idCategory", id);
        m.addObject("pagination", infoPagination);
        m.addObject("listPByPagination", listPByPagination);
        m.addObject("listPByCa", listPByCa);
        return m;
    }

}
