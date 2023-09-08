/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dto;

/**
 *
 * @author Admin
 */
public class CartDto {
    private double totalPrice;
    private int totalQuatity;
    private ProductDto product;

    public CartDto() {
    }

    public CartDto(double totalPrice, int totalQuatity, ProductDto product) {
        this.totalPrice = totalPrice;
        this.totalQuatity = totalQuatity;
        this.product = product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuatity() {
        return totalQuatity;
    }

    public void setTotalQuatity(int totalQuatity) {
        this.totalQuatity = totalQuatity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
    
    
}
