/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dto;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ProductDto {

    private long id_productt;
    private int id_category;
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

    public ProductDto() {
    }

    public ProductDto(long id_productt, int id_category, String size, String name,double price, int sale, String title, boolean highlight, boolean new_product, String detail, int id_color, String name_color, String code_color, String image, Date created_at, Date update_at) {
        this.id_productt = id_productt;
        this.id_category = id_category;
        this.size = size;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.title = title;
        this.highlight = highlight;
        this.new_product = new_product;
        this.detail = detail;
        this.id_color = id_color;
        this.name_color = name_color;
        this.code_color = code_color;
        this.image = image;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public ProductDto(long id_productt, int id_category, String size, String name, double price, double old_price, int sale, String title, boolean highlight, boolean new_product, String detail, int id_color, String name_color, String code_color, String image, Date created_at, Date update_at) {
        this.id_productt = id_productt;
        this.id_category = id_category;
        this.size = size;
        this.name = name;
        this.price = price;
        this.old_price = old_price;
        this.sale = sale;
        this.title = title;
        this.highlight = highlight;
        this.new_product = new_product;
        this.detail = detail;
        this.id_color = id_color;
        this.name_color = name_color;
        this.code_color = code_color;
        this.image = image;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }
    
    

   
    public long getId_productt() {
        return id_productt;
    }

    public void setId_productt(long id_productt) {
        this.id_productt = id_productt;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName_color() {
        return name_color;
    }

    public void setName_color(String name_color) {
        this.name_color = name_color;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCode_color() {
        return code_color;
    }

    public void setCode_color(String code_color) {
        this.code_color = code_color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNew_product() {
        return new_product;
    }

    public void setNew_product(boolean new_product) {
        this.new_product = new_product;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    

    
    
    @Override
    public String toString() {
        return "ProductDto{" + "id_productt=" + id_productt + ", id_category=" + id_category + ", size=" + size + ", name=" + name + ", price=" + price + ", sale=" + sale + ", title=" + title + ", highlight=" + highlight + ", new_product=" + new_product + ", detail=" + detail + ", id_color=" + id_color + ", name_color=" + name_color + ", code_color=" + code_color + ", image=" + image + ", created_at=" + created_at + ", update_at=" + update_at + '}';
    }

    
}
