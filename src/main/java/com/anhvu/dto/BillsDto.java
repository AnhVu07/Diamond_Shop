/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.dto;

import com.anhvu.model.Categorys;
import com.anhvu.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillsDto {

    private int bill_id;
    private String users, phone, display_name, note, address;
    private int quantity;
    private double bill_detail_total;
    private Products id_products;
    private String product_name;
    private double product_price;
    private String product_image;
    private String product_detail;
    private Categorys id_category;

   
}
