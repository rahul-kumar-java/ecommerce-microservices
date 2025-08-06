package com.rahul.ecommerce.notificationservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.kafka.topic")
public class KafkaTopicProperties {

	private String order;
	private String payment;
	private String inventory;
}
