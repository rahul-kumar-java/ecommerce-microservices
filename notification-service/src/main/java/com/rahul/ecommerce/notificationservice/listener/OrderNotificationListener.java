package com.rahul.ecommerce.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.notificationservice.config.KafkaTopicProperties;
import com.rahul.ecommerce.notificationservice.model.OrderPlacedEvent;
import com.rahul.ecommerce.notificationservice.service.NotificationService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderNotificationListener {

	private final KafkaTopicProperties topicProperties;
	private final NotificationService notificationService;
   
	@KafkaListener(topics = "#{__listener.topicProperties.order}" , groupId = "notification-group-v2", containerFactory = "kafkaListenerContainerFactory")
	public void handleOrderPlaced(OrderPlacedEvent event) {
		log.info("Received OrderPlacedEvent: {} ", event);
		log.info("Sending Notification to User ID: {} for Order ID: {}", event.getUserId(), event.getOrderId());
	
	String message = "Your Order " + event.getOrderId() + " for product " + event.getProductId() + " is confirmed!";
		
		notificationService.sendEmail(event.getUserId(), message);
		notificationService.sendSMS(event.getUserId(), message);
	
		log.info("Notification sent to User ID: {} for Order ID: {}", event.getUserId() , event.getOrderId());
	}
}
