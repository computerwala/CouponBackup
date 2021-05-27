package com.beta.usermicroservice.model;
public class UserOrderDetail {

	private String productName;
	private Long quantity;
	public UserOrderDetail(String productName, Long quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
