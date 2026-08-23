package com.rahul.ecommerce.orderservice.event;

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

	private String eventId;
	private Long orderId;
	private Long productId;
	private Integer quantity;
	
	
	
}
