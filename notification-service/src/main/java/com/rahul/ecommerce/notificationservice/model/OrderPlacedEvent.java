package com.rahul.ecommerce.notificationservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent  {

	private Long orderId;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private BigDecimal totalPrice;
	//private LocalDateTime createdAt;
}
