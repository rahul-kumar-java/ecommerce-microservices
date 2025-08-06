package com.rahul.ecommerce.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.notificationservice.config.KafkaTopicProperties;
import com.rahul.ecommerce.notificationservice.dto.OrderPlacedEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderNotificationListener {

	private final KafkaTopicProperties topicProperties;
   
	@KafkaListener(topics = "#{__listener.topicProperties.order}" , groupId = "notification-group-v2", containerFactory = "kafkaListenerContainerFactory")
	public void handleOrderPlaced(OrderPlacedEvent event) {
		log.info("Received OrderPlacedEvent: {} ", event);
		log.info("Sending Notification to User ID: {} for Order ID: {}", event.getUserId(), event.getOrderId());
	}
}
