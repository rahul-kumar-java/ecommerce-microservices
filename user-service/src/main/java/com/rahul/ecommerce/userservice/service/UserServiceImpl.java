package com.rahul.ecommerce.userservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rahul.ecommerce.userservice.dto.UserRegistrationRequest;
import com.rahul.ecommerce.userservice.dto.UserResponse;
import com.rahul.ecommerce.userservice.entity.User;
import com.rahul.ecommerce.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserResponse createUser(UserRegistrationRequest userRegistrationRequest) {
		
// DTO to Entity Mapping   
	User user=User.builder()
			.name(userRegistrationRequest.getName())
			.email(userRegistrationRequest.getEmail())
			.build();
		
		User createdUser=userRepository.save(user);
		
		// Entity to DTO Mapping
		UserResponse response=UserResponse.builder()
				.id(createdUser.getId())
				.name(createdUser.getName())
				.email(createdUser.getEmail())
				.createdAt(createdUser.getCreatedAt())
				.build();
		return response;
	}

	@Override
	public UserResponse getUserById(Long id) {
		
	User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found")
	);
	return	UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .build();
	}
}
