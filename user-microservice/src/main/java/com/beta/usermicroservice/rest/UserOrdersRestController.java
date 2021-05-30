package com.beta.usermicroservice.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.beta.usermicroservice.model.OrderDetail;
import com.beta.usermicroservice.model.Orders;
import com.beta.usermicroservice.model.User;
import com.beta.usermicroservice.model.UserOrderDetail;
import com.beta.usermicroservice.model.UserOrders;
import com.beta.usermicroservice.service.UserServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserOrdersRestController {


	@Autowired
	UserServices userService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/users/{userId}/orders")
	@HystrixCommand(fallbackMethod = "getAllOrderOfUserFallback",
					commandProperties = {	
							@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
							@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
							@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
							@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
					})
	public ResponseEntity<List<UserOrders>> getAllOrderOfUser(@PathVariable(name = "userId")Long userId) {
		try {
			ResponseEntity<List<Orders>> listOrders=restTemplate.exchange("http://orders-microservice/api/orders/user/"+userId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Orders>>() {});	
			if (listOrders != null) {
				List<UserOrders> listUserOrder=new ArrayList<UserOrders>();

				for(Orders order:listOrders.getBody()) {
					ResponseEntity<List<OrderDetail>> listOrderDetail=restTemplate.exchange("http://orders-microservice/api/orders/"+order.getId()+"/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDetail>>() {});	
					Set<UserOrderDetail> userorderDetail=new HashSet<UserOrderDetail>(); 
					for (OrderDetail  orderDetail : listOrderDetail.getBody()) {
						 userorderDetail.add(new UserOrderDetail(orderDetail.getProduct().getName(), orderDetail.getQuantity()));
					} 		
					
					listUserOrder.add(new UserOrders(order.getOrderDate(), order.getTotal(), order.getTotal(),userorderDetail));
				}
				return ResponseEntity.status(HttpStatus.OK).body(listUserOrder);
			
			} else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}
	
	public ResponseEntity<List<UserOrders>> getAllOrderOfUserFallback(@PathVariable(name = "userId")Long userId) {
		Set<UserOrderDetail> userOrderDetails=new HashSet<UserOrderDetail>();
		userOrderDetails.add(new UserOrderDetail("product", 0l));
		return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(new UserOrders(null, 0.0, 0.0, userOrderDetails)));	
	}
	
	
	
//POST SERVICE MAKING	
//	@RequestMapping(method = RequestMethod.POST, value="/users/{userId}/orders")
//	public ResponseEntity<List<UserOrders>> addOrderofUser(@RequestBody User user,@PathVariable("userId") long userId) {
//		try {
//			ResponseEntity<List<Orders>> listOrders=restTemplate.exchange("http://orders-microservice/api/orders/user/"+userId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Orders>>() {});	
//			if (listOrders != null) {
//				List<UserOrders> listUserOrder=new ArrayList<UserOrders>();
//
//				for(Orders order:listOrders.getBody()) {
//					ResponseEntity<List<OrderDetail>> listOrderDetail=restTemplate.exchange("http://orders-microservice/api/orders/"+order.getId()+"/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDetail>>() {});	
//					Set<UserOrderDetail> userorderDetail=new HashSet<UserOrderDetail>(); 
//					for (OrderDetail  orderDetail : listOrderDetail.getBody()) {
//						 userorderDetail.add(new UserOrderDetail(orderDetail.getProduct().getName(), orderDetail.getQuantity()));
//					} 		
//					
//					listUserOrder.add(new UserOrders(order.getOrderDate(), order.getTotal(), order.getTotal(),userorderDetail));
//				}
//				return ResponseEntity.status(HttpStatus.OK).body(listUserOrder);
//			
//			} else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	        }
//			}catch (Exception e) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//			}
//	}
}
