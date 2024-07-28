package com.anhvu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhvu.dto.ProductDto;
import com.anhvu.model.Categorys;
import com.anhvu.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

	@Query("SELECT p FROM Products p WHERE p.idProducts = (SELECT MAX(p2.idProducts) FROM Products p2)")
	Products getProductsNewLastest();

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c on p.idProducts = c.idProduct " + "where p.idProducts = :id "
			+ "group by p.idProducts, c.idProduct")
	ProductDto getProductDtoById(@Param("id") Long id);

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c "
			+ "on p.idProducts = c.idProduct where 1=1 and p.idProducts = (Select MAX(idProducts) FROM Products) "
			+ "group by p.idProducts, c.idProduct")
	ProductDto getProductsLatest();

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c "
			+ "on p.idProducts = c.idProduct WHERE p.name LIKE %:name% group by p.idProducts, c.idProduct")
	List<ProductDto> getProductsBySearch(@Param(value = "name") String name);

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c "
			+ "on p.idProducts = c.idProduct and p.category =:id group by p.idProducts, c.idProduct")
	List<ProductDto> getProductsByCategory(@Param(value = "id") Categorys id);

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c "
			+ "on p.idProducts = c.idProduct and p.newProduct =1 group by p.idProducts, c.idProduct")
	List<ProductDto> getListNewProducts();

	@Query("select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
			+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
			+ "from Products as p inner join Colors as c "
			+ "on p.idProducts = c.idProduct group by p.idProducts, c.idProduct")
	List<ProductDto> getListProducts();
}
