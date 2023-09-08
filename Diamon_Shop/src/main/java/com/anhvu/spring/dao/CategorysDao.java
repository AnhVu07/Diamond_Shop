/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dao;

import com.anhvu.spring.entity.Categorys;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class CategorysDao {
    public List<Categorys> getListCategorys() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.anhvu_Spring_war_1.0PU");
        EntityManager em = factory.createEntityManager();
        Query query = em.createNamedQuery("Categorys.findAll", Categorys.class);
        List<Categorys> list = query.getResultList();
        return list;
    }
}
