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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "slides")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slides.findAll", query = "SELECT s FROM Slides s")
    , @NamedQuery(name = "Slides.findById", query = "SELECT s FROM Slides s WHERE s.id = :id")
    , @NamedQuery(name = "Slides.findByImg", query = "SELECT s FROM Slides s WHERE s.img = :img")
    , @NamedQuery(name = "Slides.findByCaption", query = "SELECT s FROM Slides s WHERE s.caption = :caption")
    , @NamedQuery(name = "Slides.findByContent", query = "SELECT s FROM Slides s WHERE s.content = :content")})
public class Slides implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "img")
    private String img;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "caption")
    private String caption;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "content")
    private String content;

    public Slides() {
    }

    public Slides(Integer id) {
        this.id = id;
    }

    public Slides(Integer id, String img, String caption, String content) {
        this.id = id;
        this.img = img;
        this.caption = caption;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (!(object instanceof Slides)) {
            return false;
        }
        Slides other = (Slides) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anhvu.spring.entity.Slides[ id=" + id + " ]";
    }
    
}
