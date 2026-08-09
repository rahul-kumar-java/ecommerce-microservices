package com.rahul.ecommerce.userservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.ecommerce.userservice.dto.AuthResponse;
import com.rahul.ecommerce.userservice.dto.LoginRequest;
import com.rahul.ecommerce.userservice.dto.RefreshTokenRequest;
import com.rahul.ecommerce.userservice.entity.RefreshToken;
import com.rahul.ecommerce.userservice.entity.User;
import com.rahul.ecommerce.userservice.repository.UserRepository;
import com.rahul.ecommerce.userservice.security.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Autowired
	UserService userService;
	
public AuthResponse login(LoginRequest loginRequest)
{
	User user=userRepository.findByUsername(loginRequest.getUsername());
    String accessToken=jwtService.generateToken(user.getUsername());
    String refreshToken=refreshTokenService.createRefreshToken(user.getId());
   return new AuthResponse(accessToken,refreshToken);
}


public AuthResponse refreshToken(String token) 
{
	RefreshToken refreshToken = refreshTokenService.findByToken(token);
	refreshTokenService.validateRefreshToken(refreshToken);
    User user = userService.getUserByid(refreshToken.getUserId());
	String accessToken = jwtService.generateToken(user.getUsername());
	return new AuthResponse(accessToken, token);
}
}











