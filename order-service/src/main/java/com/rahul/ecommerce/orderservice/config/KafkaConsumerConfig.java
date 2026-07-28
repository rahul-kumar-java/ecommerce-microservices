package com.rahul.ecommerce.orderservice.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.rahul.ecommerce.orderservice.event.StockFailedEvent;
import com.rahul.ecommerce.orderservice.event.StockReservedEvent;

@Configuration
public class KafkaConsumerConfig {

	  private Map<String, Object> commonProps() {

	        Map<String, Object> props = new HashMap<>();

	        props.put(
	                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	                "172.17.154.85:9092"
	        );

	        props.put(
	                ConsumerConfig.GROUP_ID_CONFIG,
	                "order-group"
	        );
	        return props;
	    }
	  
	  
	  // Factory for StockReservedEvent

	    @Bean
	     ConsumerFactory<String, StockReservedEvent>
	    stockReservedConsumerFactory() {

	        JsonDeserializer<StockReservedEvent> deserializer =
	                new JsonDeserializer<>(StockReservedEvent.class);

	        deserializer.addTrustedPackages("*");

	        return new DefaultKafkaConsumerFactory<>(

	                commonProps(),

	                new StringDeserializer(),

	                deserializer
	        );
	    }
	    
	    @Bean
	     ConcurrentKafkaListenerContainerFactory
	    <String, StockReservedEvent>

	    stockReservedKafkaListenerFactory() {

	        ConcurrentKafkaListenerContainerFactory
	                <String, StockReservedEvent> factory =
	                new ConcurrentKafkaListenerContainerFactory<>();

	        factory.setConsumerFactory(
	                stockReservedConsumerFactory()
	        );
	        return factory;
	    }
	    
	    @Bean
	     ConsumerFactory<String, StockFailedEvent>
	    stockFailedConsumerFactory() {

	        JsonDeserializer<StockFailedEvent> deserializer =
	                new JsonDeserializer<>(StockFailedEvent.class);

	        deserializer.addTrustedPackages("*");

	        return new DefaultKafkaConsumerFactory<>(

	                commonProps(),

	                new StringDeserializer(),

	                deserializer
	        );
	    }
	    
	    @Bean
	     ConcurrentKafkaListenerContainerFactory
	    <String, StockFailedEvent>

	    stockFailedKafkaListenerFactory() {

	        ConcurrentKafkaListenerContainerFactory
	                <String, StockFailedEvent> factory =
	                new ConcurrentKafkaListenerContainerFactory<>();

	        factory.setConsumerFactory(
	                stockFailedConsumerFactory()
	        );
	        return factory;
	    }

}
