package com.rahul.ecommerce.orderservice.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rahul.ecommerce.orderservice.client.ProductServiceFeignClient;
import com.rahul.ecommerce.orderservice.client.ProductServiceRestClient;
import com.rahul.ecommerce.orderservice.dto.OrderRequest;
import com.rahul.ecommerce.orderservice.dto.OrderResponse;
import com.rahul.ecommerce.orderservice.dto.OrderStatus;
import com.rahul.ecommerce.orderservice.dto.ProductResponse;
import com.rahul.ecommerce.orderservice.entity.Order;
import com.rahul.ecommerce.orderservice.event.OrderPlacedEvent;
import com.rahul.ecommerce.orderservice.exception.InsufficientStockException;
import com.rahul.ecommerce.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductServiceFeignClient  ProductServiceFeignClient;
	
	 @Autowired
	 KafkaTemplate<String, Object> kafkaTemplate;
	/*
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {

		ProductResponse productResponse = productServiceRestClient.getProductById(orderRequest.getProductId());
	
		if(productResponse.getStockQuantity() < orderRequest.getQuantity()) {
			throw new InsufficientStockException("Insufficient Stock for Product ID: "+productResponse.getId());
		}
		
		log.info("Product fetched: {}", productResponse.getName()); // This should show "Product Service Unavailable" if fallback
		BigDecimal untiPrice = productResponse.getPrice();
		BigDecimal totalPrice = untiPrice.multiply(BigDecimal.valueOf(orderRequest.getQuantity()));
		
		// convert dto to entity
		Order order = Order.builder().userId(orderRequest.getUserId()).productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity()).totalPrice(totalPrice).build();

		Order savedOrder = orderRepository.save(order);
		
		// prepare for publish event
		OrderPlacedEvent event = OrderPlacedEvent.builder()
				                                 .orderId(savedOrder.getId())
				                                 .userId(savedOrder.getUserId())
				                                 .productId(savedOrder.getProductId())
				                                 .quantity(savedOrder.getQuantity())
				                                 .totalPrice(savedOrder.getTotalPrice())
				                                 .build();
	    kafkaTemplate.send("order-event", savedOrder.getId().toString()  ,event);  // savedOrder.getId().toString() is a message key

		// convert entity to dto

		return OrderResponse.builder().id(savedOrder.getId()).userId(savedOrder.getUserId())
				.productId(savedOrder.getProductId()).quantity(savedOrder.getQuantity())
				.totalPrice(savedOrder.getTotalPrice()).createdAt(savedOrder.getCreatedAt()).build();
	}
	*/
	 
	 
	   @Override
		public OrderResponse createOrder(OrderRequest orderRequest) {

		 // Fetch product details using Feign
		 ProductResponse productResponse = ProductServiceFeignClient.getProductById(orderRequest.getProductId());
		    BigDecimal untiPrice = productResponse.getPrice();
			BigDecimal totalPrice = untiPrice.multiply(BigDecimal.valueOf(orderRequest.getQuantity()));
			log.info("Product fetched: {}", productResponse.getName());
		 
		 // Save order as PENDING
		
		Order order=Order.builder()
		      .productId(orderRequest.getProductId())
		      .userId(orderRequest.getUserId())
		      .quantity(orderRequest.getQuantity())
		      .totalPrice(totalPrice)
		      .status(OrderStatus.PENDING)
		      .build();
		
	Order savedOrder=orderRepository.save(order);
		      

	  // prepare for publish OrderPlacedEvent
		
			OrderPlacedEvent event = OrderPlacedEvent.builder()
					                                 .orderId(savedOrder.getId())
					                                 .productId(savedOrder.getProductId())
					                                 .quantity(savedOrder.getQuantity())
					                                 .build();
					                                 
		    kafkaTemplate.send("order-event", savedOrder.getId().toString()  ,event);  // savedOrder.getId().toString() is a message key


		    return OrderResponse.builder()
		                 .orderId(savedOrder.getId())
		                 .productId(savedOrder.getProductId())
		                 .userId(savedOrder.getUserId())
		                 .quantity(savedOrder.getQuantity())
		                 .totalPrice(savedOrder.getTotalPrice())
		                 .status(OrderStatus.PENDING)
		                 .message("Order created successfully. Waiting for inventory confirmation.")
		                 .build();
		}
		
	 
	 
	@Override
	public OrderResponse getOrderById(Long id) {
		
	Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with ID: "+id));
		
   return	OrderResponse.builder()
		    .orderId(order.getId())
		    .userId(order.getUserId())
	        .productId(order.getProductId()).quantity(order.getQuantity())
	        .totalPrice(order.getTotalPrice()).createdAt(order.getCreatedAt())
	        .message("Order Confirmation")
	        .status(order.getStatus()) 
	        .build();
	}
}
