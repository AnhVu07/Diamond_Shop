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
import com.anhvu.spring.entity.Contact;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Slides;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import com.anhvu.spring.service.PaginationService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@RestController
public class HomeController {

    private final int limit = 9;

    @Autowired
    HomeServiceImp homeServiceImp;

    @Autowired
    NewProductDao newProductDao;

    @Autowired
    PaginationService paginationService;

    @Autowired
    JavaMailSender javaMailSender;

    public List<Slides> list = new ArrayList<Slides>();

    @GetMapping(value = {"/home", "/"})
    public ModelAndView index(@ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/index");
        list = homeServiceImp.getListSlides();
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        List<ProductDto> listP = new ArrayList<ProductDto>();
        List<ProductDto> listNP = new ArrayList<ProductDto>();
        List<ProductDto> listPByPagination = new ArrayList<ProductDto>();
        Products pLatest = new Products();
        Pagination pagination = new Pagination();
        listC = homeServiceImp.getListCategorys();
        listM = homeServiceImp.getListMenus();
        listP = homeServiceImp.getListProducts();
        int data = listP.size();
        pagination = paginationService.getInfoPagination(data, 1, limit);
        listPByPagination = homeServiceImp.getListPByPanigationHome(pagination.getStart(), pagination.getEnd());
        listNP = homeServiceImp.getListNewProducts();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("slides", list);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("products", listP);
        m.addObject("newproducts", listNP);
        m.addObject("pLatest", pLatest);
        m.addObject("pagination", pagination);
        m.addObject("listPByPagination", listPByPagination);
        return m;
    }

    @GetMapping("/getPByPaginationHome/{currenPage}")
    public ModelAndView getPByPaginationHome(@ModelAttribute("user") Users user, @PathVariable("currenPage") int currenPage) {
        ModelAndView m = new ModelAndView("/user/index");
        list = homeServiceImp.getListSlides();
        List<Categorys> listC = new ArrayList<Categorys>();
        List<Menus> listM = new ArrayList<Menus>();
        List<ProductDto> listP = new ArrayList<ProductDto>();
        List<ProductDto> listNP = new ArrayList<ProductDto>();
        List<ProductDto> listPByPagination = new ArrayList<ProductDto>();
        Products pLatest = new Products();
        Pagination pagination = new Pagination();
        listC = homeServiceImp.getListCategorys();
        listM = homeServiceImp.getListMenus();
        listP = homeServiceImp.getListProducts();
        int data = listP.size();
        pagination = paginationService.getInfoPagination(data, currenPage, limit);
        listPByPagination = homeServiceImp.getListPByPanigationHome(pagination.getStart(), pagination.getEnd());
        listNP = homeServiceImp.getListNewProducts();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("slides", list);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("products", listP);
        m.addObject("newproducts", listNP);
        m.addObject("pLatest", pLatest);
        m.addObject("pagination", pagination);
        m.addObject("listPByPagination", listPByPagination);
        return m;
    }

    @GetMapping(value = {"/about_us"})
    public ModelAndView about_us(@ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/about_us");
        List<Menus> listM = new ArrayList<Menus>();
        listM = homeServiceImp.getListMenus();
        m.addObject("menus", listM);
        return m;
    }

    @GetMapping(value = {"/contact"})
    public ModelAndView contact(@ModelAttribute("user") Users user, @ModelAttribute("contact") Contact contact) {
        ModelAndView m = new ModelAndView("/user/contact");
        List<Menus> listM = new ArrayList<Menus>();
        listM = homeServiceImp.getListMenus();
        m.addObject("menus", listM);
        return m;
    }

    @PostMapping(value = {"/sendMail"})
    public ModelAndView sendMail(@RequestBody @ModelAttribute("user") Users user, @ModelAttribute("contact") Contact contact) {
        ModelAndView m = new ModelAndView("/user/contact");
        try {
            if (!contact.toString().isEmpty()) {
                homeServiceImp.sendMail(contact.getEmail(), "Thank you for emailing us(Diamon Shop)", "We have received your email and will respond soon!");
                newProductDao.addContact(contact);
                System.out.println("Email sent successfully!!!");
            }
        } catch (Exception e) {
            System.out.println("Email sending failed!!!");
            m.addObject("mess", "Please enter information!");
            e.printStackTrace();
        }
        m.setViewName("redirect:contact");
        return m;
    }

    @GetMapping(value = {"/test"})
    public ModelAndView text() {
        ModelAndView m = new ModelAndView("/user/test");
        return m;
    }

    @GetMapping(value = {"/log_out"})
    public ModelAndView log_out(HttpSession session) {
        ModelAndView m = new ModelAndView();
        session.removeAttribute("infLogin");
        m.setViewName("redirect:home");
        return m;
    }

}
