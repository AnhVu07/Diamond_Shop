/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anhvu.dto.CartDto;
import com.anhvu.model.Products;
import com.anhvu.service.IProductService;

/**
 *
 * @author Admin
 */
@Repository
public class CartRepository {

    @Autowired
    IProductService productService;

    public HashMap<Long, CartDto> addCart(Long id, int quantity, HashMap<Long, CartDto> cart) {
        CartDto itemCart = new CartDto();
        Products products = productService.getProductById(id);
        if (products != null && cart.containsKey(id)) {
            itemCart = cart.get(id);
            itemCart.setTotalQuatity(itemCart.getTotalQuatity() + quantity);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        } else {
            itemCart.setTotalQuatity(quantity);
            itemCart.setProduct(products);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        }
        cart.put(id, itemCart);
        return cart;
    }

    public HashMap<Long, CartDto> updateCart(Long id, int quatity, HashMap<Long, CartDto> cart) {
        if (cart == null) {
            return cart;
        }
        CartDto itemCart = new CartDto();
        if (cart.containsKey(id)) {
            Products products = productService.getProductById(id);
            itemCart.setProduct(products);
            itemCart.setTotalQuatity(quatity);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        }
        cart.put(id, itemCart);
        return cart;
    }

    public HashMap<Long, CartDto> deleteCart(Long id, HashMap<Long, CartDto> cart) {
        if (cart == null) {
            return cart;
        }
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    public double totalPrice(HashMap<Long, CartDto> cart) {
        int totalPrice = 0;
        for (Map.Entry<Long, CartDto> entry : cart.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public double totalQuatity(HashMap<Long, CartDto> cart) {
        int totalQuatity = 0;
        for (Map.Entry<Long, CartDto> entry : cart.entrySet()) {
            totalQuatity += entry.getValue().getTotalQuatity();
        }
        return totalQuatity;
    }
}
