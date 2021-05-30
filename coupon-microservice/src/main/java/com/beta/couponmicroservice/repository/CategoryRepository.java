package com.beta.couponmicroservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beta.couponmicroservice.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}