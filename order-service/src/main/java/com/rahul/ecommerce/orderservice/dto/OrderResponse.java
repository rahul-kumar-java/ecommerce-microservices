package com.rahul.ecommerce.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderResponse {

	private Long id;
	
	private Long userId;
	
	private Long productId;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
		this.createdAt=LocalDateTime.now();
	}
}
