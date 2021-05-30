package com.beta.ordermicroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beta.ordermicroservice.model.Orders;
import com.beta.ordermicroservice.services.OrdersService;





@RestController
@RequestMapping("/api")
public class OrderRestController {

	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(method = RequestMethod.GET,value="/orders")
	public ResponseEntity<List<Orders>> getAllOrder() {
		try {
			List<Orders> orders=ordersService.getAllOrders();
			if (orders != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(orders);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				System.out.println("REST"+e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/orders/user/{userId}")
	public ResponseEntity<List<Orders>> getAllOrderByUserId(@PathVariable(name = "userId")Long userId) {
		try {
			List<Orders> orders=ordersService.getAllOrdersByUser(userId);
			if (orders != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(orders);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				System.out.println("REST"+e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}	
	
	@RequestMapping(method = RequestMethod.POST,value="/post")
	public boolean addOrder(@RequestBody Orders orders) {
		try {
			//
			System.out.println("Into call");
			return ordersService.addUserOrder(orders);
		}catch(Exception e) {
			return false;
		}
	}
	
//	@RequestMapping(method = RequestMethod.GET,value="/categories/{id}")
//	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
//		try {
//		Category category=categoryService.getCategory(id);
//		if (category != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(category);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//	}
//	
//	@RequestMapping(method = RequestMethod.POST,value="/categories")
//	public String addCategories(@RequestBody CategoryDetails categorydetail) {
//		try {
//				categoryService.addCategory(new Category(categorydetail.getName(), categorydetail.getDescription()));
//			    return "Success";
//			}catch (Exception e) {
//				return "Error";
//	    }
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT,value="/categories")
//	public String updateCategories(@RequestBody Category category) {
//		try {
//			categoryService.updateCategory(category);
//		    return "Success";
//		}catch (Exception e) {
//			return "Error";
//    }
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE,value="/categories/{id}")
//	public String deleteaddCategories(@PathVariable Long id) {
//		try {
//			categoryService.deleteCategory(id);
//		    return "Success";
//		}catch (Exception e) {
//			return "Error";
//    }
//	}

	
}
