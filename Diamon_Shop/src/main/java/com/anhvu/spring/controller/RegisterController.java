/*
02 * To change this license header, choose License Headers in Project Properties.
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@RestController
public class RegisterController {

    @Autowired
    HomeServiceImp hs;

    @Autowired
    NewProductDao newProductDao;

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView m = new ModelAndView("/user/account/register");
        List<Categorys> listC = new ArrayList<>();
        List<Menus> listM = new ArrayList<Menus>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        m.addObject("user", new Users());
        return m;
    }

    @PostMapping(value = "/dang-ky")
    public ModelAndView createAcc(@RequestBody @ModelAttribute("user") Users user, HttpServletRequest request, HttpSession session) {
        ModelAndView m = new ModelAndView("/user/account/register");
        if (!user.getPassword().isEmpty() && !user.getUser().isEmpty()) {

            if (hs.getUser(user.getUser()) == null) {
                try {
                    user.setRoles(false);
                    hs.insertUser(user);
                    session.setAttribute("status1", "Đăng ký thành công!");
                    m.setViewName("redirect:" + request.getHeader("Referer"));
                } catch (Exception e) {
                    session.setAttribute("status1", "Đăng ký thất bại!");
                    m.setViewName("redirect:" + request.getHeader("Referer"));
                }
            } else {
                session.setAttribute("status1", "Tên người dùng đã tồn tại!");
                m.setViewName("redirect:" + request.getHeader("Referer"));
            }
        } else {
            session.setAttribute("status1", "Vui lòng nhập thông tin!");
            m.setViewName("redirect:" + request.getHeader("Referer"));
        }

        return m;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@RequestBody @ModelAttribute("user") Users user, HttpSession session, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/user/account/register");
        if (!user.getUser().isEmpty() && !user.getPassword().isEmpty()) {
            user = hs.checkAccount(user);
            if (user != null) {
                m.setViewName("redirect:" + request.getHeader("Referer"));
                session.setAttribute("infLogin", user);
                session.removeAttribute("status");
                session.removeAttribute("status1");
            } else {
                session.setAttribute("status", "Sai tài khoản hoặc mật khẩu!");
                m.setViewName("redirect:" + request.getHeader("Referer"));
            }
        } else {
            session.setAttribute("status", "Vui lòng nhập thông tin!");
            m.setViewName("redirect:" + request.getHeader("Referer"));
        }

        List<Categorys> listC = new ArrayList<>();
        List<Menus> listM = new ArrayList<Menus>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        ProductDto pLatest = new ProductDto();
        pLatest = newProductDao.getProductsLatest();
        m.addObject("pLatest", pLatest);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        return m;
    }
}
