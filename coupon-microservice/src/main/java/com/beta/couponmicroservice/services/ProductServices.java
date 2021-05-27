package com.beta.couponmicroservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.beta.couponmicroservice.model.Category;
import com.beta.couponmicroservice.model.Products;
import com.beta.couponmicroservice.repository.CategoryRepository;
import com.beta.couponmicroservice.repository.ProductRepository;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Products> getAllProdcuts(Long categoryID){
		List<Products> productsList=new ArrayList<Products>();
		Category category= categoryRepository.findById(categoryID).get();
		category.getProducts().forEach(productsList::add);
		return productsList;
	}
	
	public Products getProduct(@PathVariable Long id) {
		return productRepository.findById(id).get();
	}
	
	public Products addProduct(Products products) {
			return	productRepository.save(products);
	}
	
	public void updateProduct(Products products) {
		productRepository.save(products);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}

