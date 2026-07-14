package com.rahul.ecommerce.inventoryservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rahul.ecommerce.inventoryservice.dto.InventoryRequest;
import com.rahul.ecommerce.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService=inventoryService;
	}
	
	@PostMapping
	public void createInventory(@RequestBody InventoryRequest inventoryRequest) {
		
		inventoryService.createInventory(inventoryRequest);
	}
}
