package com.rahul.ecommerce.orderservice.exception;

public class ProductServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProductServiceUnavailableException(String message) {
		super(message);
	}
}
