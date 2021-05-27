package com.beta.ordermicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beta.ordermicroservice.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
		List<Orders> findByUserId(Long userId);
}
