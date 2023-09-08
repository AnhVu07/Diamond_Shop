/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "product_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductReview.findAll", query = "SELECT p FROM ProductReview p")
    , @NamedQuery(name = "ProductReview.findById", query = "SELECT p FROM ProductReview p WHERE p.id = :id")
    , @NamedQuery(name = "ProductReview.findByName", query = "SELECT p FROM ProductReview p WHERE p.name = :name")
    , @NamedQuery(name = "ProductReview.findByEmail", query = "SELECT p FROM ProductReview p WHERE p.email = :email")
    , @NamedQuery(name = "ProductReview.findByEvaluate", query = "SELECT p FROM ProductReview p WHERE p.evaluate = :evaluate")})
public class ProductReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 10)
    @Column(name = "evaluate")
    private String evaluate;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment")
    private String comment;
//    @JoinColumn(name = "id_user", referencedColumnName = "id")
//    @ManyToOne
    private int idUser;
//    @JoinColumn(name = "id_product", referencedColumnName = "id_products")
//    @ManyToOne(optional = false)
    private int idProduct;

    public ProductReview() {
    }

    public ProductReview(Integer id, String name, String email, String evaluate, String comment, int idUser, int idProduct) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.evaluate = evaluate;
        this.comment = comment;
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    
    
    public ProductReview(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductReview)) {
            return false;
        }
        ProductReview other = (ProductReview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "id=" + id + ", name=" + name + ", email=" + email + ", evaluate=" + evaluate + ", comment=" + comment + ", idUser=" + idUser + ", idProduct=" + idProduct + '}';
    }

    
    
}
