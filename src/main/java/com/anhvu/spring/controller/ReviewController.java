/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.anhvu.spring.dao.NewProductDao;
import com.anhvu.spring.entity.ProductReview;
import com.anhvu.spring.entity.Users;
import com.anhvu.spring.service.HomeServiceImp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@RestController
public class ReviewController {

    @Autowired
    HomeServiceImp hs;

    @Autowired
    NewProductDao newProductDao;

    @PostMapping(value = "/submit_review")
    public ModelAndView addReview(@ModelAttribute("user") Users user, @ModelAttribute("product_review") ProductReview product_review, @RequestParam(value = "image", required = false) MultipartFile multipartFile, HttpSession session, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/user/detail");
        Users user1 = (Users) session.getAttribute("infLogin");
        if (user1 != null) {
            if (!product_review.getEvaluate().isEmpty() && !product_review.getComment().isEmpty()) {
                product_review.setIdUser(user1.getId());
                newProductDao.addReview(product_review);
                session.removeAttribute("status");
                m.setViewName("redirect:" + request.getHeader("Referer"));
            } else {
                session.setAttribute("status", "Vui lòng nhập thông tin!");
                m.setViewName("redirect:" + request.getHeader("Referer"));
            }
        } else {
            session.setAttribute("status", "Please login!");
            m.setViewName("redirect:home");
        }
        return m;
    }
}
