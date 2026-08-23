package com.rahul.ecommerce.orderservice.service;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.orderservice.client.ProductServiceFeignClient;
import com.rahul.ecommerce.orderservice.dto.OrderRequest;
import com.rahul.ecommerce.orderservice.dto.OrderResponse;
import com.rahul.ecommerce.orderservice.dto.OrderStatus;
import com.rahul.ecommerce.orderservice.dto.ProductResponse;
import com.rahul.ecommerce.orderservice.entity.Order;
import com.rahul.ecommerce.orderservice.event.OrderPlacedEvent;
import com.rahul.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductServiceFeignClient  ProductServiceFeignClient;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	 
	 
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
					                                 .eventId(UUID.randomUUID().toString())
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
