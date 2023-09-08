/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dao;

import com.anhvu.spring.dto.CartDto;
import com.anhvu.spring.dto.ProductDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class CartDao {

    @Autowired
    NewProductDao np;

    public HashMap<String, CartDto> addCart(String id,int quantity, HashMap<String, CartDto> cart) {
        CartDto itemCart = new CartDto();
        ProductDto product = (ProductDto) np.getProductsById(id);
        if (product != null && cart.containsKey(id)) {
            itemCart = cart.get(id);
            itemCart.setTotalQuatity(itemCart.getTotalQuatity() + quantity);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        } else {
            itemCart.setTotalQuatity(quantity);
            itemCart.setProduct(product);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        }
        cart.put(id, itemCart);
        return cart;
    }

    public HashMap<String, CartDto> updateCart(String id, int quatity, HashMap<String, CartDto> cart) {
        if (cart == null) {
            return cart;
        }
        CartDto itemCart = new CartDto();
        if (cart.containsKey(id)) {
            ProductDto product = (ProductDto) np.getProductsById(id);
            itemCart.setProduct(product);
            itemCart.setTotalQuatity(quatity);
            itemCart.setTotalPrice(itemCart.getTotalQuatity() * itemCart.getProduct().getPrice());
        }
        cart.put(id, itemCart);
        return cart;
    }

    public HashMap<String, CartDto> deleteCart(String id, HashMap<String, CartDto> cart) {
        if (cart == null) {
            return cart;
        }
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    public double totalPrice(HashMap<String, CartDto> cart) {
        int totalPrice = 0;
        for (Map.Entry<String, CartDto> entry : cart.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public double totalQuatity(HashMap<String, CartDto> cart) {
        int totalQuatity = 0;
        for (Map.Entry<String, CartDto> entry : cart.entrySet()) {
            totalQuatity += entry.getValue().getTotalQuatity();
        }
        return totalQuatity;
    }
}
