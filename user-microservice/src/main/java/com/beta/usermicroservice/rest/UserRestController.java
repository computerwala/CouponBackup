package com.beta.usermicroservice.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beta.usermicroservice.model.User;
import com.beta.usermicroservice.service.UserServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@FeignClient(name="user-service" )
@RibbonClient(name="user-service")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(method = RequestMethod.GET,value="/users")
	public ResponseEntity<List<User>> getAllUser() {
		try {
			List<User> users=userService.getAllUser();
			if (users != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(users);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/users/{id}")
	@HystrixCommand(fallbackMethod = "getUserFallBack",
	commandProperties = {	
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
	})
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		try {
		User user=userService.getUser(id);
		if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	public ResponseEntity<User> getUserFallBack(@PathVariable Long id) {
		try {
			  return ResponseEntity.status(HttpStatus.OK).body(new User("first Name", "last Name","phone"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
			
	
	@RequestMapping(method = RequestMethod.POST,value="/users")
	public HashMap<String, String> addUser(@RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		try {
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			user.setJoinDate(ts);
				userService.addUser(user);
			    map.put("message", "success");
				return map;
			} catch (Exception e) {
				map.put("message", "error");
				return map;
	    }
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/users")
	public HashMap<String, String> updateUser(@RequestBody User user) {
		HashMap<String, String> map = new HashMap<>();
		try {
			userService.updateUser(user);
			map.put("message", "success");
			return map;
		} catch (Exception e) {
			map.put("message", "error");
			return map;
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/users/{id}")
	public  HashMap<String, String> deleteUser(@PathVariable Long id) {
		HashMap<String, String> map = new HashMap<>();
		try {
			userService.deleteUser(id);
			map.put("message", "success");
			return map;
		} catch (Exception e) {
			map.put("message", "error");
			return map;
		}
	}
}
