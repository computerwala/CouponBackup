package com.beta.ordermicroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beta.ordermicroservice.model.OrderDetail;
import com.beta.ordermicroservice.services.OrderDetailService;





@RestController
@RequestMapping("/api")
public class OrderDetailRestController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping(method = RequestMethod.GET,value="/orders/{orderId}/products")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetails(@PathVariable(name = "orderId")Long orderId) {
		try {
			List<OrderDetail> orderDetail=orderDetailService.getAllOrderDetail(orderId);
			if (orderDetail != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(orderDetail);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				System.out.println("REST"+e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}
	
}
