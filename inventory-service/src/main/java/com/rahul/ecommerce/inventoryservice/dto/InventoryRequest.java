package com.rahul.ecommerce.inventoryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class InventoryRequest {

	private long productId;
	
	private String name;
	
	private Integer availableQuantity;
	
	private Integer reservedQuantity;
	
}
