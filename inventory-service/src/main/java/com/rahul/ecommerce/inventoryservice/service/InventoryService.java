package com.rahul.ecommerce.inventoryservice.service;

import com.rahul.ecommerce.inventoryservice.dto.InventoryRequest;

public interface InventoryService {
   
	void createInventory(InventoryRequest inventoryRequest);
}
