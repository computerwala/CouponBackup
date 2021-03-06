package com.beta.usermicroservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.beta.usermicroservice.model.User;
import com.beta.usermicroservice.model.UserOrderDetail;
import com.beta.usermicroservice.model.UserOrders;
import com.beta.usermicroservice.repository.UserRepository;


@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		try {
			return (List<User>)userRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	public User getUser(Long id) {
		try {
			return userRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void addUser(User user) {
		try {
			userRepository.save(user);

		} catch (Exception e) {

		}
	}

	public void updateUser(User user) {
		try {
			userRepository.save(user);

		} catch (Exception e) {

		}
	}

	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);

		} catch (Exception e) {

		}
	}

//	public boolean userService(UserOrders user) {
//		// TODO Auto-generated method stub
//		user ne kis din orderKiya total , userID, orderId
//		for(UserOrderDetail detail:user.getUserOrderDetails()) {
//			UserOrder c = new UserOder();
//			userId;
//			orderId;
//			productId;
//			productPrice;
//			productQuantity;
//			
//		}
//	}

}
