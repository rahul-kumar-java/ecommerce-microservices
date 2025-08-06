package com.rahul.ecommerce.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rahul.ecommerce.orderservice.dto.OrderRequest;
import com.rahul.ecommerce.orderservice.dto.OrderResponse;
import com.rahul.ecommerce.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {

		OrderResponse response = orderService.createOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {

		OrderResponse response = orderService.getOrderById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
