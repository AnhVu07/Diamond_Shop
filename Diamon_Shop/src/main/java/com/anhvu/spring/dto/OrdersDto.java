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
public class OrdersDto {

    private int bill_id;
    private String users, phone, display_name, note, address;
    private int quantity;
    private double bill_detail_total;
    private long id_products;
    private String product_name;
    private double product_price;
    private String product_image;
    private String product_detail;
    private int id_category;

    public OrdersDto() {
    }

    public OrdersDto(int bill_id, String users, String phone, String display_name, String note, String address, int quatity, double bill_detail_total, long id_products, String product_name, double product_price, String product_image, String product_detail, int id_category) {
        this.bill_id = bill_id;
        this.users = users;
        this.phone = phone;
        this.display_name = display_name;
        this.note = note;
        this.address = address;
        this.quantity = quatity;
        this.bill_detail_total = bill_detail_total;
        this.id_products = id_products;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_detail = product_detail;
        this.id_category = id_category;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuatity() {
        return quantity;
    }

    public void setQuatity(int quatity) {
        this.quantity = quatity;
    }

    public double getBill_detail_total() {
        return bill_detail_total;
    }

    public void setBill_detail_total(double bill_detail_total) {
        this.bill_detail_total = bill_detail_total;
    }

    public long getId_products() {
        return id_products;
    }

    public void setId_products(long id_products) {
        this.id_products = id_products;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "OrdersDto{" + "bill_id=" + bill_id + ", users=" + users + ", phone=" + phone + ", display_name=" + display_name + ", note=" + note + ", address=" + address + ", quatity=" + quantity + ", bill_detail_total=" + bill_detail_total + ", id_products=" + id_products + ", product_name=" + product_name + ", product_price=" + product_price + ", product_image=" + product_image + ", product_detail=" + product_detail + ", id_category=" + id_category + '}';
    }
    
    

}
