package com.beta.couponmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beta.couponmicroservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}