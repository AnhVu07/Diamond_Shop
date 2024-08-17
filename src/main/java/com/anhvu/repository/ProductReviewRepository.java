package com.anhvu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anhvu.model.ProductReview;
import com.anhvu.model.Products;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer>{
	
	@Query("SELECT pr FROM ProductReview pr WHERE pr.product = :idProduct")
	List<ProductReview> getListProductReviews(@Param(value = "idProduct") Products product);

}
