/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import javax.el.ELException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.anhvu.exception.ProductNotFoundException;
import com.anhvu.exception.UserNotFoundException;



/**
 *
 * @author Admin
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NullPointerException.class,BindException.class})
    public ModelAndView exception(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("/user/error");
        exception.printStackTrace();
        return modelAndView;
    }

    
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Du lieu khong dung dinh dang. " + ex.getMessage();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleMethodIllegalStateException(IllegalStateException ex) {
    	ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    	ex.printStackTrace();
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
    	ex.printStackTrace();
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExistsException(EntityExistsException  ex) {
    	ex.printStackTrace();
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException  ex) {
    	ex.printStackTrace();
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui long nhap dung du lieu!");
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
    	ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(ELException.class)
    public ResponseEntity<String> handleELException(ELException ex) {
    	ex.printStackTrace();
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User khong tim thay de thuc thi tac vu!");
    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
    	ex.printStackTrace();
        String message = "Khong tim thay trang yeu cau!";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(NotFoundException ex) {
    	ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    	ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay user!");
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    	ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vi phạm nguyen tac toan ven du lieu!");
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String message = "Phuong thuc yeu cau khong đuoc ho tro!";
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(message);
    }

}
