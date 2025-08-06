package com.rahul.ecommerce.productservice.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRequest {

	private String name;
	
	private BigDecimal price;
	
	private Integer stockQuantity;
	
}
