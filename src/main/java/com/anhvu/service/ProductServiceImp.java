package com.anhvu.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.anhvu.dto.ProductDto;
import com.anhvu.exception.ProductNotFoundException;
import com.anhvu.model.Categorys;
import com.anhvu.model.ProductReview;
import com.anhvu.model.Products;
import com.anhvu.repository.ProductRepository;
import com.anhvu.repository.ProductReviewRepository;
import com.anhvu.utils.UserInputSanitizer;

@Service
@Transactional
public class ProductServiceImp implements IProductService {
	
	private final Path root = Paths.get("src/main/resources/static/assets/user/img_review");

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductReviewRepository productReviewRepository;
	
	@Autowired
	IHomeService homeService;

	@Override
	@Transactional(readOnly = true)
	public List<Products> getListProducts() {
		
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Products getProductById(long id) {
		
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("product with id: "+id+" does not exist"));
	}

	@Override
	public Products getProductsNewLastest() {

		return productRepository.getProductsNewLastest();
	}

	@Override
	public void addProduct(Products product) {
	    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	    product.setCreatedAt(currentDate);
	    product.setUpdateAt(currentDate);

	    try {
	        sanitizeProductFields(product);
	        productRepository.save(product);
	    } catch (Exception e) {
	        throw new EntityExistsException("Add product failed", e);
	    }
	}

	private void sanitizeProductFields(Products product) {
	    product.setDetail(UserInputSanitizer.sanitizer(product.getDetail()));
	    product.setTitle(UserInputSanitizer.sanitizer(product.getTitle()));
	    product.setSize(UserInputSanitizer.sanitizer(product.getSize()));
	    product.setName(UserInputSanitizer.sanitizer(product.getName()));
	}

	@Override
	@Transactional
	public void updateProduct(Products updatedProduct) {
	    Products existingProduct = getProductById(updatedProduct.getIdProducts());

	    if (updatedProduct != null && !updatedProduct.toString().isEmpty()) {
	        updateProductFields(existingProduct, updatedProduct);
	        existingProduct.setUpdateAt(new java.sql.Date(System.currentTimeMillis()));
	    }
	}

	private void updateProductFields(Products existingProduct, Products updatedProduct) {
	    existingProduct.setTitle(UserInputSanitizer.sanitizer(updatedProduct.getTitle()));
	    existingProduct.setDetail(UserInputSanitizer.sanitizer(updatedProduct.getDetail()));
	    existingProduct.setName(UserInputSanitizer.sanitizer(updatedProduct.getName()));
	    existingProduct.setSize(UserInputSanitizer.sanitizer(updatedProduct.getSize()));

	    existingProduct.setHighlight(updatedProduct.getHighlight());
	    
	    existingProduct.setCategory(updatedProduct.getCategory());
	    existingProduct.setImage(updatedProduct.getImage());
	    existingProduct.setNewProduct(updatedProduct.getNewProduct());
	    existingProduct.setPrice(updatedProduct.getPrice());
	    existingProduct.setSale(updatedProduct.getSale());
	}

	@Override
	public void deleteProduct(long id) {
		getProductById(id);
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductDto> getListProductDtos() {

		return productRepository.getListProducts();
	}

	@Override
	public ProductDto getProductDtoById(long id) {

		return productRepository.getProductDtoById(id);
	}

	@Override
	public ProductDto getProductDtoLatest() {

		return productRepository.getProductsLatest();
	}

	@Override
	public List<ProductDto> getListPByPanigationHome(int start, int end) {
		String queryString = "select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
				+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
				+ "from Products as p inner join Colors as c "
				+ "on p.idProducts = c.idProduct group by p.idProducts, c.idProduct";

		TypedQuery<ProductDto> query = entityManager.createQuery(queryString, ProductDto.class);

		query.setMaxResults(end);
		query.setFirstResult(start);

		return query.getResultList();
	}

	@Override
	public List<ProductDto> getListPByPanigation(@Param(value = "id") Categorys id, int start, int end) {

		String queryString = "select new com.anhvu.dto.ProductDto(p.idProducts,p.category,p.size,p.name,p.price,p.old_price,p.sale,"
				+ "p.title,p.highlight,p.newProduct,p.detail,c.id,c.name,c.code,c.image,p.createdAt,p.updateAt) "
				+ "from Products as p inner join Colors as c "
				+ "on p.idProducts = c.idProduct and p.category =:id group by p.idProducts, c.idProduct";

		TypedQuery<ProductDto> query = entityManager.createQuery(queryString, ProductDto.class);

		query.setParameter("id", id);
		query.setMaxResults(end);
		query.setFirstResult(start);

		return query.getResultList();
	}
	

	@Override
	public List<ProductDto> getProductsBySearch(String name) {

		return productRepository.getProductsBySearch(name);
	}

	@Override
	public List<ProductDto> getProductsByCategory(Categorys id) {

		return productRepository.getProductsByCategory(id);
	}

	@Override
	public List<ProductDto> getListNewProducts() {

		return productRepository.getListNewProducts();
	}

	@Override
	public List<ProductReview> getProductsReview(Products id) {
		
		return productReviewRepository.getListProductReviews(id);
	}

	@Override
	public ProductReview addReview(ProductReview productReview) {
		setSanitizerProduct(productReview);
		
		return productReviewRepository.save(productReview);
	}
	
	private void setSanitizerProduct(ProductReview productReview) {
		String name = UserInputSanitizer.sanitizer(productReview.getName());
		String email = UserInputSanitizer.sanitizer(productReview.getEmail());
		String comment = UserInputSanitizer.sanitizer(productReview.getComment());
		String productImage = UserInputSanitizer.sanitizer(productReview.getProductImage());
		productReview.setName(name);
		productReview.setEmail(email);
		productReview.setComment(comment);
		productReview.setProductImage(productImage);
	}

	@Override
	public boolean exitsById(long id) {
		return productRepository.existsById(id);
	}

	@Override
	public List<ProductReview> getListReview() {
		
		return productReviewRepository.findAll();
	}

	@Override
	public void deleteReview(int id) {
		productReviewRepository.findById(id)
		.orElseThrow(() -> new IllegalStateException("productReview with id: "+id+" does not exist"));
		
		productReviewRepository.deleteById(id);
		
	}
	
	@Override
	public String saveFile(MultipartFile file) throws IOException {
	    if (file == null || file.isEmpty()) {
	        throw new IllegalArgumentException("File is null or empty");
	    }

	    Files.createDirectories(root);

	    String originalFilename = file.getOriginalFilename();
	    String fileExtension = getFileExtension(originalFilename);
	    String uniqueFilename = generateUniqueFilename(fileExtension);

	    Path destinationFile = this.root.resolve(uniqueFilename);

	    try (InputStream inputStream = file.getInputStream()) {
	        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
	    }

	    return uniqueFilename;
	}

	private String generateUniqueFilename(String fileExtension) {
	    String uniqueId = UUID.randomUUID().toString();
	    return fileExtension.isEmpty() ? uniqueId : uniqueId + "." + fileExtension;
	}

	private String getFileExtension(String filename) {
	    return Optional.ofNullable(filename)
	        .filter(f -> f.contains("."))
	        .map(f -> f.substring(f.lastIndexOf(".") + 1))
	        .orElse("");
	}
}
