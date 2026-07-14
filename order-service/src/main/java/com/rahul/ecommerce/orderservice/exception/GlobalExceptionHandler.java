package com.rahul.ecommerce.orderservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductServiceUnavailableException.class)
	public ResponseEntity<?> handleProductServiceNotAvailableException(Exception ex) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", LocalDateTime.now());
		map.put("status", 503);
		map.put("message", "Service is unavailable");
		map.put("error", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(map);
	}
	
	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<Map<String, Object>>  handleInsufficientStockException(InsufficientStockException ex) {
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Insufficient Stock");
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body , HttpStatus.BAD_REQUEST);
	}
}
