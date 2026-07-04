package com.rahul.ecommerce.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rahul.ecommerce.userservice.dto.AuthResponse;
import com.rahul.ecommerce.userservice.dto.LoginRequest;
import com.rahul.ecommerce.userservice.entity.User;
import com.rahul.ecommerce.userservice.repository.UserRepository;
import com.rahul.ecommerce.userservice.security.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
public AuthResponse login(LoginRequest loginRequest)
{
	User user=userRepository.findByUsername(loginRequest.getUsername());
	
	if(user==null)
		throw new RuntimeException("User not found");
	
	if(!user.getPassword().equals(loginRequest.getPassword())) {
		throw new RuntimeException("Invalid password");
	}
 String token=jwtService.generateToken(user.getUsername());
 return new AuthResponse(token);
}
}











