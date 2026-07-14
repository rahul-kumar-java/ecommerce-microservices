package com.rahul.ecommerce.productservice.dto;

import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class InventoryRequest {

	private String name;
	
	private long productId;
	
	private Integer availableQuantity;
	
	private Integer reservedQuantity;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
		this.createdAt=LocalDateTime.now();
	}
}
