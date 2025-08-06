package com.rahul.ecommerce.productservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductResponse {

    private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private Integer stockQuantity;
	
	private LocalDateTime createdAt; 
}
