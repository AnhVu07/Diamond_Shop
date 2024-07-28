/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.service;

import java.util.HashMap;

import com.anhvu.dto.CartDto;

/**
 *
 * @author Admin
 */

 public interface ICartDto {

     HashMap<Long, CartDto> addCart(Long id,int quantity, HashMap<Long, CartDto> cart);

     HashMap<Long, CartDto> updateCart(Long id, int quatity, HashMap<Long, CartDto> cart);

     HashMap<Long, CartDto> deleteCart(Long id, HashMap<Long, CartDto> cart);

     double totalPrice(HashMap<Long, CartDto> cart);

     double totalQuatity(HashMap<Long, CartDto> cart);
    
     boolean isNumber(String str);
}
