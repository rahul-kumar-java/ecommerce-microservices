package com.rahul.ecommerce.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.rahul.ecommerce.productservice.dto.InventoryRequest;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryServiceFeignClient {

	@PostMapping("/api/inventory")
	public void createInventory(@RequestBody InventoryRequest inventoryRequest);
}
