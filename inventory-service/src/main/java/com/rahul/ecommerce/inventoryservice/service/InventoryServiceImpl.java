package com.rahul.ecommerce.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.inventoryservice.dto.InventoryRequest;
import com.rahul.ecommerce.inventoryservice.entity.Inventory;
import com.rahul.ecommerce.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Override
	public void createInventory(InventoryRequest inventoryRequest) {

	Inventory entity=Inventory.builder()
		                .productId(inventoryRequest.getProductId())
		                .name(inventoryRequest.getName())
		                .availableQuantity(inventoryRequest.getAvailableQuantity())
		                .reservedQuantity(inventoryRequest.getReservedQuantity())
		                .build();
	
		inventoryRepository.save(entity);
	}
}
