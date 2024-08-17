package com.anhvu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.anhvu.dto.ProductDto;
import com.anhvu.model.Categorys;
import com.anhvu.model.ProductReview;
import com.anhvu.model.Products;


public interface IProductService {
	
	List<Products> getListProducts();
	
	Products getProductById(long id);
	
	Products getProductsNewLastest();
	
	void addProduct(Products products);
	
	void updateProduct(Products products);
	
	void deleteProduct(long id);
	
	boolean exitsById(long id);
	
	
	List<ProductDto> getListProductDtos();
	
	ProductDto getProductDtoById(long id);
	
	ProductDto getProductDtoLatest();
	
	List<ProductDto> getListPByPanigationHome(int start,int end);
	
	List<ProductDto> getListPByPanigation(Categorys id, int start, int end);
	
	List<ProductDto> getProductsBySearch(String name);
	
	List<ProductDto> getProductsByCategory(Categorys id);
	
	List<ProductDto> getListNewProducts();
	
	
	List<ProductReview> getProductsReview(Products id);
	
	ProductReview addReview(ProductReview productReview);
	
	List<ProductReview> getListReview();
	
	void deleteReview(int id);

	String saveFile(MultipartFile file) throws IOException;
}
