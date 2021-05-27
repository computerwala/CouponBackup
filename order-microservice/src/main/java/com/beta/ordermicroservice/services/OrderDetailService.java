package com.beta.ordermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beta.ordermicroservice.model.OrderDetail;
import com.beta.ordermicroservice.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	public List<OrderDetail> getAllOrderDetail(Long orderId){
		try {
	        List<OrderDetail> listOrderDetail=orderDetailRepository.findByOrderId(orderId);
			System.out.println(listOrderDetail.size());
	        return listOrderDetail;
		}catch (Exception e) {
			return null;
		}
	}
	
}
