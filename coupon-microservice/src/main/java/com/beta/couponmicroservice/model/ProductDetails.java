package com.beta.couponmicroservice.model;

import java.math.BigDecimal;

import java.util.Set;

public class ProductDetails {

    private String couponCode;
	private String description;
	private BigDecimal quantity;
	private BigDecimal price;
	private Set<Long> categories;
	
	public ProductDetails(String couponCode, String description, BigDecimal quantity, BigDecimal price, Set<Long> categories) {
		super();
		this.couponCode = couponCode;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.categories = categories;
	}
	
	public String getcouponCode() {
		return couponCode;
	}
	public void setcouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Set<Long> getCategories() {
		return categories;
	}
	public void setCategories(Set<Long> categories) {
		this.categories = categories;
	}	
}