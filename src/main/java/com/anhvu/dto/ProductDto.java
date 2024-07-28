/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.dto;

import java.util.Date;

import com.anhvu.model.Categorys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id_productt;
    private Categorys id_category;
    private String size, name;
    private double price;
    private double old_price;
    private int sale;
    private String title;
    private boolean highlight, new_product;
    private String detail;
    private int id_color;
    private String name_color;
    private String code_color, image;
    private Date created_at, update_at;

   
}
