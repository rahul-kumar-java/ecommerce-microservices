package com.rahul.ecommerce.orderservice.service;

import com.rahul.ecommerce.orderservice.dto.OrderRequest;
import com.rahul.ecommerce.orderservice.dto.OrderResponse;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest orderRequest);
	
	public OrderResponse getOrderById(Long id);
}
