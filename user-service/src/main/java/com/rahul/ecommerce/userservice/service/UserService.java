package com.rahul.ecommerce.userservice.service;

import com.rahul.ecommerce.userservice.dto.UserRegistrationRequest;
import com.rahul.ecommerce.userservice.dto.UserResponse;
import com.rahul.ecommerce.userservice.entity.User;

public interface UserService {

	public UserResponse createUser(UserRegistrationRequest userRegistrationRequest);
	public UserResponse getUserById(Long id);
	public User getUserByid(Long userId);
}
