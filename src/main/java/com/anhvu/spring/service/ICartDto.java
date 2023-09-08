/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.service;

import com.anhvu.spring.dto.CartDto;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public interface ICartDto {

    public HashMap<String, CartDto> addCart(String id,int quantity, HashMap<String, CartDto> cart);

    public HashMap<String, CartDto> updateCart(String id, int quatity, HashMap<String, CartDto> cart);

    public HashMap<String, CartDto> deleteCart(String id, HashMap<String, CartDto> cart);

    public double totalPrice(HashMap<String, CartDto> cart);

    public double totalQuatity(HashMap<String, CartDto> cart);
}
