package com.rahul.ecommerce.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.productservice.dto.ProductRequest;
import com.rahul.ecommerce.productservice.dto.ProductResponse;
import com.rahul.ecommerce.productservice.entity.Product;
import com.rahul.ecommerce.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {

		// Convert dto into entity
	Product entity = Product.builder()
		       .name(productRequest.getName())
		       .price(productRequest.getPrice())
		       .stockQuantity(productRequest.getStockQuantity())
		       .build();
		
	Product createdProduct=productRepository.save(entity);   
		
		//convert entity into dto
	
ProductResponse response = ProductResponse.builder()
	               .id(createdProduct.getId())
	               .name(createdProduct.getName())
	               .price(createdProduct.getPrice())
	               .stockQuantity(createdProduct.getStockQuantity())
	               .createdAt(createdProduct.getCreatedAt())
	               .build();
	
		return response;
	}

	@Override
	public ProductResponse getProductById(Long id) {
		
	Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource Not Found"));
		
	ProductResponse response = ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .stockQuantity(product.getStockQuantity())
            .createdAt(product.getCreatedAt())
            .build();

	return response;
	}
}
