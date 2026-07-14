package com.rahul.ecommerce.inventoryservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.inventoryservice.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	Optional<Inventory> findByProductId(Long productId);

}
