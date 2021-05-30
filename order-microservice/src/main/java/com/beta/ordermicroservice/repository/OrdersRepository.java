package com.beta.ordermicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beta.ordermicroservice.model.Orders;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
		List<Orders> findByUserId(Long userId);
}
