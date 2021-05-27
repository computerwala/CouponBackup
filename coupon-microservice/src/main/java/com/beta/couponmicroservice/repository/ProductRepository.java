package com.beta.couponmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beta.couponmicroservice.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	
}
