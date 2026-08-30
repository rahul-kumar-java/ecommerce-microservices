package com.rahul.ecommerce.inventoryservice.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent  {

	private String eventId;
	private Long orderId;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private BigDecimal totalPrice;
    	
	
	
	@Override
	public String toString() {
		return "OrderPlacedEvent [eventId=" + eventId + ", orderId=" + orderId + ", userId=" + userId + ", productId="
				+ productId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
}
