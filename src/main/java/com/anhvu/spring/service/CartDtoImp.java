/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.service;

import com.anhvu.spring.dao.CartDao;
import com.anhvu.spring.dto.CartDto;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CartDtoImp implements ICartDto {

    @Autowired
    private CartDao cartDao = new CartDao();

    @Override
    public HashMap<String, CartDto> addCart(String id,int quantity, HashMap<String, CartDto> cart) {
        return cartDao.addCart(id,quantity, cart);
    }

    @Override
    public HashMap<String, CartDto> updateCart(String id, int quatity, HashMap<String, CartDto> cart) {
        return cartDao.updateCart(id, quatity, cart);
    }

    @Override
    public HashMap<String, CartDto> deleteCart(String id, HashMap<String, CartDto> cart) {
        return cartDao.deleteCart(id, cart);
    }

    @Override
    public double totalPrice(HashMap<String, CartDto> cart) {
        return cartDao.totalPrice(cart);
    }

    @Override
    public double totalQuatity(HashMap<String, CartDto> cart) {
        return cartDao.totalQuatity(cart);
    }

}
