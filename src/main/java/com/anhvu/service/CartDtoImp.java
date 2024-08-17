/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.service;


import com.anhvu.dto.CartDto;
import com.anhvu.repository.CartRepository;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.*;

/**
 *
 * @author Admin
 */
@Service
public class CartDtoImp implements ICartDto {

    @Autowired
    private CartRepository cartRepository = new CartRepository();

    @Override
    public HashMap<Long, CartDto> addCart(Long id,int quantity, HashMap<Long, CartDto> cart) {
        return cartRepository.addCart(id,quantity, cart);
    }

    @Override
    public HashMap<Long, CartDto> updateCart(Long id, int quatity, HashMap<Long, CartDto> cart) {
        return cartRepository.updateCart(id, quatity, cart);
    }

    @Override
    public HashMap<Long, CartDto> deleteCart(Long id, HashMap<Long, CartDto> cart) {
        return cartRepository.deleteCart(id, cart);
    }

    @Override
    public double totalPrice(HashMap<Long, CartDto> cart) {
        return cartRepository.totalPrice(cart);
    }

    @Override
    public double totalQuatity(HashMap<Long, CartDto> cart) {
        return cartRepository.totalQuatity(cart);
    }
    
    @Override
    public boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


}
