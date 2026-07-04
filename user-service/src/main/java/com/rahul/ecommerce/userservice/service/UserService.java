package com.rahul.ecommerce.userservice.service;

import com.rahul.ecommerce.userservice.dto.UserRegistrationRequest;
import com.rahul.ecommerce.userservice.dto.UserResponse;

public interface UserService {

	public UserResponse createUser(UserRegistrationRequest userRegistrationRequest);
	public UserResponse getUserById(Long id);
}
