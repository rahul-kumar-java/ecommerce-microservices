package com.rahul.ecommerce.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.inventoryservice.entity.ProcessedEvent;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, Long> {

	boolean existsByEventId(String eventId);
}
