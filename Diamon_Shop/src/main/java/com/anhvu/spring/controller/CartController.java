/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.dto.CartDto;
import com.anhvu.spring.entity.Bills;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.CartDtoImp;
import com.anhvu.spring.service.HomeServiceImp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class CartController {

    @Autowired
    CartDtoImp cartDtoImp;

    @Autowired
    HomeServiceImp hs;

    @Autowired
    NewProductDao newProductDao;

    @RequestMapping("/cart/{id}/{quantity}")
    public String cart(@PathVariable("id") String id, @PathVariable("quantity") int quantity, HttpSession session, HttpServletRequest request) {
        HashMap<String, CartDto> cart = (HashMap<String, CartDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        Users user1 = (Users) session.getAttribute("infLogin");
        if (user1 != null) {
            cart = cartDtoImp.addCart(id, quantity, cart);
            int totalQuatity = (int) cartDtoImp.totalQuatity(cart);
            double totalPrice = (double) cartDtoImp.totalPrice(cart);
            session.setAttribute("cart", cart);
            session.setAttribute("totalQuatity", totalQuatity);
            session.setAttribute("totalPrice", totalPrice);
            session.removeAttribute("status");
        } else {
            session.setAttribute("status", "Please login!");
        }

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/cart")
    public ModelAndView cart(@ModelAttribute("user") Users user) {
        ModelAndView m = new ModelAndView("/user/cart");
        List<Menus> listM = new ArrayList<Menus>();
        listM = hs.getListMenus();
        m.addObject("menus", listM);
        return m;
    }

    @RequestMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable String id, HttpSession session) {
        HashMap<String, CartDto> cart = (HashMap<String, CartDto>) session.getAttribute("cart");
        cartDtoImp.deleteCart(id, cart);
        session.setAttribute("cart", cart);
        int totalQuatity = (int) cartDtoImp.totalQuatity(cart);
        double totalPrice = (double) cartDtoImp.totalPrice(cart);
        session.setAttribute("totalQuatity", totalQuatity);
        session.setAttribute("totalPrice", totalPrice);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/editCart/{id}/{quaty}")
    public String editCart(@PathVariable("id") String id, @PathVariable("quaty") int quaty, HttpSession session) {
        ModelAndView m = new ModelAndView();
        HashMap<String, CartDto> cart = (HashMap<String, CartDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        try {
            if (quaty > 0) {
                if (quaty == 0) {
                    cart = cartDtoImp.deleteCart(id, cart);
                } else {
                    cart = cartDtoImp.updateCart(id, quaty, cart);
                }
            }
        } catch (Exception e) {
            System.out.println("Nhap so luong khong dung!");
        }
        session.setAttribute("cart", cart);
        int totalQuatity = (int) cartDtoImp.totalQuatity(cart);
        double totalPrice = (double) cartDtoImp.totalPrice(cart);
        session.setAttribute("totalQuatity", totalQuatity);
        session.setAttribute("totalPrice", totalPrice);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(@ModelAttribute("bill") Bills bill, @ModelAttribute("user") Users user, HttpSession session) {
        ModelAndView m = new ModelAndView("/user/checkout/checkout");
        List<Categorys> listC = new ArrayList<>();
        List<Menus> listM = new ArrayList<>();
        listC = hs.getListCategorys();
        listM = hs.getListMenus();
        Users info = (Users) session.getAttribute("infLogin");
        if (info != null) {
            bill.setDisplayName(info.getDisplayName());
            bill.setUsers(info.getUser());
            bill.setAddress(info.getAddress());
        }
        Products pLatest = new Products();
        pLatest = newProductDao.getProductsIdNewLastest();
        m.addObject("pLatest", pLatest);
        m.addObject("bill", bill);
        m.addObject("categorys", listC);
        m.addObject("menus", listM);
        return m;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView checkoutBill(@ModelAttribute("bill") Bills bill, @ModelAttribute("user") Users user, HttpSession session, HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        HashMap<String, CartDto> cart = (HashMap<String, CartDto>) session.getAttribute("cart");
        if (cart != null) {
            if (!bill.getAddress().isEmpty() && !bill.getNote().isEmpty()) {
                bill.setQuatity((int) session.getAttribute("totalQuatity"));
                bill.setTotal((double) session.getAttribute("totalPrice"));
                try {
                    Date date = new Date();
                    java.sql.Date created_at = new java.sql.Date(date.getTime());
                    bill.setCreated_at(created_at);
                    hs.insertBill(bill);
                    hs.addBillDetail(cart);
                    session.removeAttribute("status");
                    session.removeAttribute("cart");
                    session.removeAttribute("totalQuatity");
                    session.removeAttribute("totalPrice");
                    m.setViewName("redirect:home");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("status", "Vui lòng nhập đầy đủ thông tin!");
                m.setViewName("redirect:" + request.getHeader("Referer"));
            }
        } else {
            session.setAttribute("status", "Giỏ hàng rỗng!");
            m.setViewName("redirect:cart");
        }
        return m;
    }

}
