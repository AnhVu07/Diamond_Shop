/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findByIdProducts", query = "SELECT p FROM Products p WHERE p.idProducts = :idProducts")
    , @NamedQuery(name = "Products.findBySize", query = "SELECT p FROM Products p WHERE p.size = :size")
    , @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name")
    , @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price")
    , @NamedQuery(name = "Products.findByImage", query = "SELECT p FROM Products p WHERE p.image = :image")
    , @NamedQuery(name = "Products.findBySale", query = "SELECT p FROM Products p WHERE p.sale = :sale")
    , @NamedQuery(name = "Products.findByTitle", query = "SELECT p FROM Products p WHERE p.title = :title")
    , @NamedQuery(name = "Products.findByHighlight", query = "SELECT p FROM Products p WHERE p.highlight = :highlight")
    , @NamedQuery(name = "Products.findByNewProduct", query = "SELECT p FROM Products p WHERE p.newProduct = :newProduct")
    , @NamedQuery(name = "Products.findByCreatedAt", query = "SELECT p FROM Products p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Products.findByUpdateAt", query = "SELECT p FROM Products p WHERE p.updateAt = :updateAt")})
public class Products implements Serializable {

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduct")
//    private Collection<ProductReview> productReviewCollection;
//    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
//    @ManyToOne(optional = false)
//    private Categorys idCategory;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_products")
    private long idProducts;
    @Size(max = 255)
    @Column(name = "size")
    private String size;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "old_price")
    private double old_price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "image")
    private String image;
    @Column(name = "sale")
    private Integer sale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "highlight")
    private Boolean highlight;
    @Column(name = "new_product")
    private Boolean newProduct;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "detail")
    private String detail;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
//    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
//    @ManyToOne(optional = false)
    private int idCategory;

    public Products() {
    }

    

    public Products(long idProducts, String size, String name, double price,double old_price, String image, Integer sale, String title, Boolean highlight, Boolean newProduct, String detail, Date createdAt, Date updateAt, int idCategory) {
        this.idProducts = idProducts;
        this.size = size;
        this.name = name;
        this.price = price;
        this.old_price = old_price;
        this.image = image;
        this.sale = sale;
        this.title = title;
        this.highlight = highlight;
        this.newProduct = newProduct;
        this.detail = detail;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.idCategory = idCategory;
    }

   

    

    public long getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(long idProducts) {
        this.idProducts = idProducts;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public Boolean getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Boolean newProduct) {
        this.newProduct = newProduct;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    @Override
    public String toString() {
        return "Products{" + "idProducts=" + idProducts + ", size=" + size + ", name=" + name + ", price=" + price + ", old_price=" + old_price + ", image=" + image + ", sale=" + sale + ", title=" + title + ", highlight=" + highlight + ", newProduct=" + newProduct + ", detail=" + detail + ", createdAt=" + createdAt + ", updateAt=" + updateAt + ", idCategory=" + idCategory + '}';
    }


    

   

  



    
  
    
}
