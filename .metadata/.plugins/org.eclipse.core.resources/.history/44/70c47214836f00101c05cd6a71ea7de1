package com.rahul.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rahul.ecommerce.orderservice.dto.ProductResponse;
import com.rahul.ecommerce.orderservice.service.ProductServiceClientWrapper;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceFeignClient {
	
	@GetMapping("/api/products/{id}")
	ProductResponse getProductById(@PathVariable("id") Long id);
}
