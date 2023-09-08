/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.service;

import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Bills;
import com.anhvu.spring.entity.Billsdetail;
import com.anhvu.spring.entity.Categorys;
import com.anhvu.spring.entity.Menus;
import com.anhvu.spring.entity.Slides;
import com.anhvu.spring.entity.Users;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public interface IHomeService {

    public List<Slides> getListSlides();

    public List<Categorys> getListCategorys();

    public List<Menus> getListMenus();

    public List<ProductDto> getListProducts();

    public List<ProductDto> getListNewProducts();

    public List<ProductDto> getProductsByCategory(String id);

    public ProductDto getProductsById(String id);

    public List<ProductDto> getProductsBySearch(String name);
    
    public boolean insertUser(Users user);
    
    public Users getUser(String user);
    
    public boolean insertBill(Bills bill);
    
     public Bills getLastestBill();
     
      public List<ProductDto> getListPByPanigationHome(int start, int end);
    
}
