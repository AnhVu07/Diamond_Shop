package com.anhvu.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.anhvu.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {
	
    private final PaymentService paymentService;
    
    @GetMapping("/vn-pay")
    public RedirectView  pay(HttpServletRequest request) throws ParseException {
    	
        return new RedirectView(paymentService.createVnPayPayment(request));
    }
    
    @GetMapping("/vn-pay-callback")
    public ModelAndView payCallbackHandler(@RequestParam("vnp_ResponseCode") String status) {
    	ModelAndView view = new ModelAndView("/user/checkout/paymentStatus");
    	
        if (status.equals("00")) {
        	view.addObject("status", "Thanh Toán Thành Công!");
            return view;
        } else {
        	view.addObject("status", "Thanh Toán Thất Bại!");
            return view;
        }
    }
}
