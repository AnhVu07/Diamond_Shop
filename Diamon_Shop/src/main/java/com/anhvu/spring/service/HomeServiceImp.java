/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.service;

import com.anhvu.spring.dao.CategorysDao;
import com.anhvu.spring.dao.MenusDao;
import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.dao.ProductsDao;
import com.anhvu.spring.dao.SlidesDao;
import com.anhvu.spring.dto.CartDto;
import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Bills;
import com.anhvu.spring.entity.Billsdetail;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Slides;
import com.anhvu.spring.entity.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HomeServiceImp implements IHomeService {

    @Autowired
    SlidesDao sd;

    @Autowired
    CategorysDao cd;

    @Autowired
    MenusDao md;

    @Autowired
    ProductsDao pd;
    
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    NewProductDao np;

    @Override
    public List<Slides> getListSlides() {
        return sd.getListSlides();
    }

    @Override
    public List<Categorys> getListCategorys() {
        return cd.getListCategorys();
    }

    @Override
    public List<Menus> getListMenus() {
        return md.getListMenus();
    }

    @Override
    public List<ProductDto> getListProducts() {
        return pd.getListProducts();
    }

    @Override
    public List<ProductDto> getListNewProducts() {
        return np.getListNewProducts();
    }

    @Override
    public List<ProductDto> getProductsByCategory(String id) {
        return np.getProductsByCategory(id);
    }

    @Override
    public ProductDto getProductsById(String id) {
        return np.getProductsById(id);
    }

    @Override
    public List<ProductDto> getProductsBySearch(String name) {
        return np.getProductsBySearch(name);
    }

    @Override
    public boolean insertUser(Users user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        return np.insertUser(user);
    }

    @Override
    public Users getUser(String user) {
        return np.getUser(user);
    }

    public Users checkAccount(Users user) {
        String pass = user.getPassword();
        user = np.getAccount(user);
        if (user != null) {
            if (BCrypt.checkpw(pass, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean insertBill(Bills bill) {
        return np.insertBill(bill);
    }

    @Override
    public Bills getLastestBill() {
        return np.getLastestBill();
    }

    public void addBillDetail(HashMap<String, CartDto> cart) {

        Bills idb = np.getLastestBill();
        for (Map.Entry<String, CartDto> itemCart : cart.entrySet()) {
            Billsdetail bd = new Billsdetail();
            bd.setIdBill(idb.getId());
            bd.setIdProducts(itemCart.getValue().getProduct().getId_productt());
            bd.setQuatity(itemCart.getValue().getTotalQuatity());
            bd.setTotal(itemCart.getValue().getTotalPrice());
            np.addBillDetail(bd);
        }

    }
    
    @Async
     public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        javaMailSender.send(mailMessage);
    }

    @Override
    public List<ProductDto> getListPByPanigationHome(int start, int end) {
        return pd.getListPByPanigationHome(start, end);
    }

}
