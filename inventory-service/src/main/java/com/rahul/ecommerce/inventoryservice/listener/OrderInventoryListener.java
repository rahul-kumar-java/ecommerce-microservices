package com.rahul.ecommerce.inventoryservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.inventoryservice.config.KafkaTopicProperties;
import com.rahul.ecommerce.inventoryservice.entity.Inventory;
import com.rahul.ecommerce.inventoryservice.event.StockFailedEvent;
import com.rahul.ecommerce.inventoryservice.event.StockReservedEvent;
import com.rahul.ecommerce.inventoryservice.model.OrderPlacedEvent;
import com.rahul.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderInventoryListener {

	private final KafkaTopicProperties topicProperties;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@KafkaListener(
	        topics = "order-event",
	        groupId = "inventory-group",
	        containerFactory = "kafkaListenerContainerFactory"
	    )
	 public void handleOrderPlaced(OrderPlacedEvent event) {
	
		 log.info("Received OrderPlacedEvent: {}", event);
		 
		 Inventory inventory = inventoryRepository
	                .findByProductId(event.getProductId())
	                .orElse(null);
		 System.out.println("check inventory:  "+inventory);
		  if (inventory == null || inventory.getAvailableQuantity() < event.getQuantity()) {

	            log.info("Stock not available for productId: {}", event.getProductId());

	            StockFailedEvent failedEvent = new StockFailedEvent(
	                    event.getOrderId(),
	                    event.getProductId(),
	                    "Insufficient stock"
	            );
	            kafkaTemplate.send(
	                    "stock-failed-topic",
	                    event.getOrderId().toString(),
	                    failedEvent
	            );
		 return;
	}
		  
		// Reduce stock
		  
		  inventory.setAvailableQuantity(
	                inventory.getAvailableQuantity() - event.getQuantity()
	        );
		  inventoryRepository.save(inventory);
		  log.info("Stock reserved for orderId: {}", event.getOrderId());
		  StockReservedEvent reservedEvent = new StockReservedEvent(
	                event.getOrderId(),
	                event.getProductId(),
	                event.getQuantity()
	        );
		   kafkaTemplate.send(
	                "stock-reserved-topic",
	                event.getOrderId().toString(),
	                reservedEvent
	        );
}
}


















