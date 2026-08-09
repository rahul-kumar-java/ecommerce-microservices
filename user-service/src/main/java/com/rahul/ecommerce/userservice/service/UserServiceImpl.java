package com.rahul.ecommerce.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.userservice.dto.UserRegistrationRequest;
import com.rahul.ecommerce.userservice.dto.UserResponse;
import com.rahul.ecommerce.userservice.entity.User;
import com.rahul.ecommerce.userservice.exception.UserNotFoundException;
import com.rahul.ecommerce.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserResponse createUser(UserRegistrationRequest userRegistrationRequest) {
		
// DTO to Entity Mapping
		
		
	User user=User.builder()
			.username(userRegistrationRequest.getName())
			.email(userRegistrationRequest.getEmail())
			.password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
			.build();
		
		User createdUser=userRepository.save(user);
		
		// Entity to DTO Mapping
		UserResponse response=UserResponse.builder()
				.id(createdUser.getId())
				.name(createdUser.getUsername())
				.email(createdUser.getEmail())
				.createdAt(createdUser.getCreatedAt())
				.build();
		return response;
	}

	@Override
	public UserResponse getUserById(Long id) {
		
	User user =  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found with id: "+id)
	);
	return	UserResponse.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .build();
	}

	@Override
	public User getUserByid(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	}
}






