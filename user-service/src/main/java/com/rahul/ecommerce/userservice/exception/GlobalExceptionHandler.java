package com.rahul.ecommerce.userservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> getUserNotFoundException(UserNotFoundException ex, HttpServletRequest request)
	{
		Map<String, Object> map=new HashMap<>();
		map.put("timestamp", LocalDateTime.now());
		map.put("status", HttpStatus.NOT_FOUND.value());
		map.put("message", "User Not Found");
		map.put("error", ex.getMessage());
		map.put("path", request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
}
