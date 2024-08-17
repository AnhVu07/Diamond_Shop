/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.service;



import java.util.List;

import com.anhvu.model.Categorys;
import com.anhvu.model.Contact;
import com.anhvu.model.Menus;
import com.anhvu.model.Slides;

/**
 *
 * @author Admin
 */

public interface IHomeService {
	
    List<Slides> getListSlides();
    
    List<Categorys> getListCategorys();
    
    void addCategory(Categorys categorys);
    
    void updateCategorys(Categorys categorys);
    
    void deleteCategorys(int id);
    
    Categorys getCategoryById(int id);
    
    List<Menus> getListMenus();
    
    List<Contact> getListContacts();
    
    Contact addContact(Contact contact);
    
    void deleteContact(int id);
    
    boolean exitsByIdContac(int id);
    
    void sendMail(String to, String subject, String content);
}
