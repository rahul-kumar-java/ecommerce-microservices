package com.rahul.ecommerce.inventoryservice.listener;

import java.time.LocalDateTime;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.inventoryservice.config.KafkaTopicProperties;
import com.rahul.ecommerce.inventoryservice.entity.Inventory;
import com.rahul.ecommerce.inventoryservice.entity.ProcessedEvent;
import com.rahul.ecommerce.inventoryservice.event.StockFailedEvent;
import com.rahul.ecommerce.inventoryservice.event.StockReservedEvent;
import com.rahul.ecommerce.inventoryservice.model.OrderPlacedEvent;
import com.rahul.ecommerce.inventoryservice.repository.InventoryRepository;
import com.rahul.ecommerce.inventoryservice.repository.ProcessedEventRepository;
import org.springframework.transaction.annotation.Transactional;
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
	private final InventoryRepository inventoryRepository;
	private final ProcessedEventRepository processedEventRepository;

	
	@RetryableTopic(attempts = "3", backoff = @Backoff(delay = 2000))
	@Transactional
	@KafkaListener(topics = "order-event", groupId = "inventory-group", containerFactory = "kafkaListenerContainerFactory")
	public void handleOrderPlaced(OrderPlacedEvent event) {

		log.info("Received OrderPlacedEvent: {}", event.toString());
         
		// Idempotency check

		if (processedEventRepository.existsByEventId(event.getEventId())) {
			log.info("Event already processed. Ignoring eventId: {}", event.getEventId());
			return;
		}

		// Find inventory

		Inventory inventory = inventoryRepository.findByProductId(event.getProductId()).orElse(null);
		log.info("check inventory: {}  " + inventory);

		// Check stock

		if (inventory == null || inventory.getAvailableQuantity() < event.getQuantity()) {

			log.info("Stock not available for productId: {}", event.getProductId());

			// Mark event as processed

			ProcessedEvent processedEvent = ProcessedEvent.builder().eventId(event.getEventId())
					.processedAt(LocalDateTime.now()).build();
			processedEventRepository.save(processedEvent);

			// Publish failure event

			StockFailedEvent failedEvent = new StockFailedEvent(event.getOrderId(), event.getProductId(),
					"Insufficient stock");
			kafkaTemplate.send("stock-failed-topic", event.getOrderId().toString(), failedEvent);
			return;
		}

		// Reduce stock

		inventory.setAvailableQuantity(inventory.getAvailableQuantity() - event.getQuantity());
		inventoryRepository.save(inventory);
		log.info("Stock reserved for orderId: {}", event.getOrderId());

		// Mark event as processed

		ProcessedEvent processedEvent = ProcessedEvent.builder().eventId(event.getEventId())
				.processedAt(LocalDateTime.now()).build();
		processedEventRepository.save(processedEvent);

		log.info("Stock reserved and event marked as processed. eventId:  {}", event.getEventId());
		

		// Publish success event

		StockReservedEvent reservedEvent = new StockReservedEvent(event.getOrderId(), event.getProductId(),
				event.getQuantity());
		kafkaTemplate.send("stock-reserved-topic", event.getOrderId().toString(), reservedEvent);

		log.info("StockReservedEvent published for orderId: {}", event.getOrderId());

	}
	
	@DltHandler
	public void handleDlt(OrderPlacedEvent event) {

	    log.error(
	        "OrderPlacedEvent moved to DLT. eventId={}, orderId={}, productId={}, quantity={}",
	        event.getEventId(),
	        event.getOrderId(),
	        event.getProductId(),
	        event.getQuantity()
	    );
	}
}




