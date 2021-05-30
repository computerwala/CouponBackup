package com.beta.couponmicroservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beta.couponmicroservice.model.Products;

@Repository
public interface ProductRepository extends CrudRepository<Products, Long> {
	
}
