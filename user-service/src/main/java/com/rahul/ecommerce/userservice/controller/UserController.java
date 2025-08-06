package com.rahul.ecommerce.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rahul.ecommerce.userservice.dto.UserRegistrationRequest;
import com.rahul.ecommerce.userservice.dto.UserResponse;
import com.rahul.ecommerce.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
		
	UserResponse response =	userService.createUser(userRegistrationRequest);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
		
	UserResponse response =	userService.getUserById(id);
	return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}
}
