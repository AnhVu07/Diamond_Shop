/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Admin
 */
@Entity
public class Billsdetail {

    @Id
    private Integer id;

    private int quatity;

    private double total;

    private Integer idBill;

    private Long idProducts;

    public Billsdetail() {
    }

    public Billsdetail(Integer id, int quatity, double total, Integer idBill, Long idProducts) {
        this.id = id;
        this.quatity = quatity;
        this.total = total;
        this.idBill = idBill;
        this.idProducts = idProducts;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Long getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(Long idProducts) {
        this.idProducts = idProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
