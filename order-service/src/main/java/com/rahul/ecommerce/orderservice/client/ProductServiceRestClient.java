package com.rahul.ecommerce.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.rahul.ecommerce.orderservice.dto.ProductResponse;
import com.rahul.ecommerce.orderservice.exception.ProductServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductServiceRestClient {

	@Autowired
	RestTemplate restTemplate;
	
	public final String BASE_URL="http://localhost:9093/api/products";
	
	int retryCount=1;
	//@CircuitBreaker(name="product_service", fallbackMethod="getProductByIdFallback")
	@Retry(name="product_service", fallbackMethod="getProductByIdFallback")
	public ProductResponse getProductById(Long productId) {
		
	String url = BASE_URL+"/"+productId;
	log.info("Retry Count: {} : ", retryCount);
	retryCount++;
	return restTemplate.getForObject(url, ProductResponse.class);
	
	}
	
	public ProductResponse getProductByIdFallback(Long productId, Throwable t) {
		 log.warn("Retry Mechanism: Fallback triggered for getProductById. Reason: {}", t.getMessage());
		
		 throw new ProductServiceUnavailableException("Product Service is unavailable");
		 
		}
}
