package com.rahul.ecommerce.orderservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.orderservice.dto.OrderStatus;
import com.rahul.ecommerce.orderservice.entity.Order;
import com.rahul.ecommerce.orderservice.event.StockFailedEvent;
import com.rahul.ecommerce.orderservice.event.StockReservedEvent;
import com.rahul.ecommerce.orderservice.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceListener {

	@Autowired
	OrderRepository orderRepository;
	
	 @KafkaListener(
	            topics = "stock-reserved-topic",
	            groupId = "order-group",
	            containerFactory = "stockReservedKafkaListenerFactory"
	    )
	public void handleStockReserved(StockReservedEvent event) {
		
		log.info("Stock Reserved for orderId:  {}", event.getOrderId());
		
	Order order=orderRepository.findById(event.getOrderId()).orElseThrow();
	order.setStatus(OrderStatus.CONFIRMED);
	orderRepository.save(order);
	}
	 
	 
	 
	 @KafkaListener(
	            topics = "stock-failed-topic",
	            groupId = "order-group",
	            containerFactory = "stockFailedKafkaListenerFactory"
	    )
	 public void handleStockFailed(StockFailedEvent event) {
		 
		 log.info("Stock Failed with orderId:  {}", event.getOrderId());
		 
		Order order =orderRepository.findById(event.getOrderId()).orElseThrow();
		order.setStatus(OrderStatus.FAILED);
		 orderRepository.save(order);
	 }
	 
	 
}
















