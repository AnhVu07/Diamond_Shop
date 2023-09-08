/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.net.ConnectException;
import javax.security.auth.login.AccountNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author Admin
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NullPointerException.class, IllegalArgumentException.class, NumberFormatException.class, BindException.class,NoHandlerFoundException.class})
    public ModelAndView exception(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("/user/error");
        exception.printStackTrace();
        return modelAndView;
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {BadRequestException.class, NoHandlerFoundException.class})
//    public ModelAndView NotFind(Exception ex) {
//        ModelAndView modelAndView = new ModelAndView("/user/404");
//        ex.printStackTrace();
//        return modelAndView;
//    }
}
