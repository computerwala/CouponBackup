package com.beta.ordermicroservice.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "orderDate")
	private Date orderDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Products product;

	@Column(name = "quantity")
	
	private Long quantity;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private Orders order;

	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDetail(Date orderDate, Products product, Long quantity, Orders order) {
		super();
		this.orderDate = orderDate;
		this.product = product;
		this.quantity = quantity;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}
