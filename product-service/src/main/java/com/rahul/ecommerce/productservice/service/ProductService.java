package com.rahul.ecommerce.productservice.service;

import com.rahul.ecommerce.productservice.dto.ProductRequest;
import com.rahul.ecommerce.productservice.dto.ProductResponse;

public interface ProductService {

	public ProductResponse createProduct(ProductRequest productRequest);
	
	public ProductResponse getProductById(Long id);
}
