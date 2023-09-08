/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.dao.UsersDao;
import com.anhvu.spring.dto.OrdersDto;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Contact;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminController {

    @Autowired
    HomeServiceImp homeServiceImp;

    @Autowired
    NewProductDao newProductDao;

    @Autowired
    UsersDao usersDao;

    @GetMapping(value = {"/manager_products"})
    public ModelAndView manager(@ModelAttribute("user") Users user, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/manager_product");
        List<Products> listP = new ArrayList<Products>();
        List<Categorys> listC = new ArrayList<Categorys>();
        listP = newProductDao.getListProducts();
        listC = homeServiceImp.getListCategorys();
        m.addObject("categorys", listC);
        m.addObject("listP", listP);
        return m;
    }

    @GetMapping("/manager_contact")
    public ModelAndView managerContact(@ModelAttribute("user") Users user, @ModelAttribute("contact") Contact contact) {
        ModelAndView m = new ModelAndView("/admin/manager_contact");
        List<Contact> listContact = new ArrayList<Contact>();
        List<Menus> listM = new ArrayList<Menus>();
        listM = homeServiceImp.getListMenus();
        listContact = newProductDao.getListContacts();
        m.addObject("listContact", listContact);
        m.addObject("menus", listM);
        return m;
    }

    @GetMapping(value = "/deleteContact/{id}")
    public ModelAndView deleteContact(@ModelAttribute("user") Users user, @ModelAttribute("contact") Contact contact, @PathVariable("id") int id) {
        ModelAndView m = new ModelAndView("/admin/manager_contact");
        List<Contact> listContact = new ArrayList<Contact>();
        List<Menus> listM = new ArrayList<Menus>();
        if (id > 0) {
            newProductDao.deleteContact(id);
        }
        listM = homeServiceImp.getListMenus();
        listContact = newProductDao.getListContacts();
        m.addObject("listContact", listContact);
        m.addObject("menus", listM);
        return m;
    }

    @PostMapping(value = "/sendFeedback")
    public ModelAndView sendContact(@ModelAttribute("user") Users user, @ModelAttribute("contact") Contact contact) {
        ModelAndView m = new ModelAndView("/admin/manager_contact");
        if (!contact.getEmail().isEmpty()) {
            try {
                homeServiceImp.sendMail(contact.getEmail(), contact.getSubject(), contact.getContent());
                System.out.println("Email sent successfully!!!");
            } catch (Exception e) {
                System.out.println("Email sending failed!!!");
                m.addObject("mess", "Please enter information!");
                e.printStackTrace();
            }
        }
        m.setViewName("redirect:manager_contact");
        return m;
    }

    @GetMapping(value = {"/returnAdmin"})
    public ModelAndView returnAdmin(@ModelAttribute("user") Users user, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/manager_product");
        m.setViewName("redirect:manager_products");
        return m;
    }

    @GetMapping(value = {"/manager_orders"})
    public ModelAndView managerOrders(@ModelAttribute("user") Users user, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/admin/manager_orders");
        List<OrdersDto> listOrder = new ArrayList<OrdersDto>();
        List<Menus> listM = new ArrayList<Menus>();
        List<String[]> list = new ArrayList<String[]>();
        listOrder = newProductDao.getListOrders();
        listM = homeServiceImp.getListMenus();
        list = newProductDao.getDataOrders();
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (String[] item : list) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("orderMonth", Integer.parseInt(item[0]));
            dataItem.put("revenue", Double.parseDouble(item[1]));
            dataItem.put("ngay", item[2]);
            dataList.add(dataItem);
        }
        String data = new Gson().toJson(dataList);
        m.addObject("listOrder", listOrder);
        m.addObject("menus", listM);
        request.setAttribute("listData", data);
        request.setAttribute("listOrders", dataList);
        return m;
    }

    @GetMapping(value = {"/load/{id}"})
    public ModelAndView load(@ModelAttribute("user") Users user, @PathVariable("id") String id, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/edit");
        List<Categorys> listC = new ArrayList<Categorys>();
        Products PDetail = new Products();
        PDetail = newProductDao.getProductsId(id);
        listC = homeServiceImp.getListCategorys();
        m.addObject("categorys", listC);
        m.addObject("detail", PDetail);
        return m;
    }

    @GetMapping(value = {"/delete/{id}"})
    public ModelAndView delete(@ModelAttribute("user") Users user, @PathVariable("id") int id, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/manager_product");
        newProductDao.deleteProduct(id);
        List<Products> listP = new ArrayList<Products>();
        List<Categorys> listC = new ArrayList<Categorys>();
        listP = newProductDao.getListProducts();
        listC = homeServiceImp.getListCategorys();
        m.addObject("categorys", listC);
        m.addObject("listP", listP);
        return m;
    }

    @PostMapping(value = {"/addProduct"})
    public ModelAndView insertProduct(@RequestBody @ModelAttribute("user") Users user, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/manager_product");
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Date updateDate = new java.sql.Date(date.getTime());
        product.setCreatedAt(sqlDate);
        product.setUpdateAt(updateDate);
        newProductDao.addProduct(product);
        m.setViewName("redirect:manager_products");
        return m;
    }

    @PostMapping(value = {"/editProduct"})
    public ModelAndView updateProduct(@RequestBody @ModelAttribute("user") Users user, @ModelAttribute("product") Products product) {
        ModelAndView m = new ModelAndView("/admin/manager_product");
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Date updateDate = new java.sql.Date(date.getTime());
        product.setCreatedAt(sqlDate);
        product.setUpdateAt(updateDate);
        newProductDao.updateProduct(product);
        m.setViewName("redirect:manager_products");
        return m;
    }

    @GetMapping("/deleteBills/{id}")
    public ModelAndView deleteBills(@ModelAttribute("user") Users user, @PathVariable("id") int id, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/admin/manager_orders");
        newProductDao.deleteBills(id);
        List<OrdersDto> listOrder = new ArrayList<OrdersDto>();
        List<Menus> listM = new ArrayList<Menus>();
        List<String[]> list = new ArrayList<String[]>();
        listOrder = newProductDao.getListOrders();
        list = newProductDao.getDataOrders();
        listM = homeServiceImp.getListMenus();
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (String[] item : list) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("orderMonth", Integer.parseInt(item[0]));
            dataItem.put("revenue", Double.parseDouble(item[1]));
            dataItem.put("ngay", item[2]);
            dataList.add(dataItem);
        }
        String data = new Gson().toJson(dataList);
        m.addObject("listOrder", listOrder);
        m.addObject("menus", listM);
        request.setAttribute("listData", data);
        request.setAttribute("listOrders", dataList);
        return m;
    }

    @GetMapping(value = "/manager_accounts")
    public ModelAndView manager_accounts(@ModelAttribute("user") Users user, @ModelAttribute("account") Users account) {
        ModelAndView m = new ModelAndView("/admin/manager_accounts");
        List<Users> listUser = new ArrayList<>();
        listUser = usersDao.getListUsers();
        m.addObject("listUser", listUser);
        return m;
    }

    @PostMapping(value = "/addAcc")
    public ModelAndView addAcc(@RequestBody @ModelAttribute("user") Users user, @ModelAttribute("account") Users account) {
        ModelAndView m = new ModelAndView("/admin/manager_accounts");
        if (account != null && !account.toString().isEmpty()) {
            homeServiceImp.insertUser(account);
        } else {
            m.addObject("message", "Vui lòng nhập đủ thông tin!");
        }
        m.setViewName("redirect:manager_accounts");
        return m;
    }

    @GetMapping(value = "/deleteAcc/{id}")
    public ModelAndView deleteAcc(@ModelAttribute("user") Users user, @PathVariable("id") int id, @ModelAttribute("account") Users account) {
        ModelAndView m = new ModelAndView("/admin/manager_accounts");
        try {
            usersDao.deleteUser(id);
            List<Users> listUser = new ArrayList<>();
            listUser = usersDao.getListUsers();
            m.addObject("listUser", listUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    @GetMapping(value = "/loadAcc/{id}")
    public ModelAndView loadAcc(@ModelAttribute("user") Users user, @PathVariable("id") int id, @ModelAttribute("account") Users account) {
        ModelAndView m = new ModelAndView("/admin/edit_Acc");
        Users getUsersById = usersDao.getUsersById(id);
        m.addObject("getUsersById", getUsersById);
        return m;
    }

    @PostMapping(value = "/editAcc")
    public ModelAndView updateAccount(@RequestBody @ModelAttribute("user") Users user, @ModelAttribute("account") Users account) {
        ModelAndView m = new ModelAndView("/admin/manager_accounts");
        if (account != null && !account.toString().isEmpty()) {
            usersDao.updateUser(user);
        } else {
            m.addObject("message", "Vui lòng nhập đủ thông tin!");
        }
        m.setViewName("redirect:manager_accounts");
        return m;
    }
}
