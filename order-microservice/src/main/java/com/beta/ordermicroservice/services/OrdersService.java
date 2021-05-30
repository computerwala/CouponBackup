package com.beta.ordermicroservice.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beta.ordermicroservice.model.Orders;
import com.beta.ordermicroservice.repository.OrdersRepository;



@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<Orders> getAllOrders()
	{
		try {
			return (List<Orders>)ordersRepository.findAll();
		}catch (Exception e) {
			System.out.println("SERVICE"+e.getMessage());

			return null;
		}
	
	}
	
	public List<Orders> getAllOrdersByUser(Long userId)
	{
		try {
			return ordersRepository.findByUserId(userId);
		}catch (Exception e) {
			System.out.println("SERVICE"+e.getMessage());

			return null;
		}
	}
	
	public Orders getOrder(Long id) {
		try {
			return ordersRepository.findById(id).get();
		
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public void addOrder(Orders order) {
		ordersRepository.save(order);
	}
	  
	public void orderOrder(Orders order) {
		ordersRepository.save(order);
	}
	
	public void deleteOrder(Long id) {
		ordersRepository.deleteById(id);
	}

	public boolean addUserOrder(Orders orders) {
		// TODO Auto-generated method stub
		try
		{
			ordersRepository.save(orders);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	
}
