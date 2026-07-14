package com.rahul.ecommerce.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private Long id;
	private String name;
	private BigDecimal price;
	private Integer stockQuantity;
	private LocalDateTime createdAt;
	
}
