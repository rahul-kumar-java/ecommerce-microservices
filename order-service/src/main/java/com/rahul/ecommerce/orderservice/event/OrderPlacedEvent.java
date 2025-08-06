package com.rahul.ecommerce.orderservice.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlacedEvent {

	private Long orderId;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private BigDecimal totalPrice;
	//private LocalDateTime createdAt;
	
	
	
}
