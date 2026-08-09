package com.rahul.ecommerce.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.ecommerce.userservice.dto.AuthResponse;
import com.rahul.ecommerce.userservice.dto.LoginRequest;
import com.rahul.ecommerce.userservice.dto.RefreshTokenRequest;
import com.rahul.ecommerce.userservice.security.JwtService;
import com.rahul.ecommerce.userservice.service.AuthService;
import com.rahul.ecommerce.userservice.service.RefreshTokenService;
import com.rahul.ecommerce.userservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthService authService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		return authService.login(loginRequest);
	}

	
	
	@PostMapping("/refresh-token")
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

		return authService.refreshToken(refreshTokenRequest.getRefreshToken());
	}
}

	
	
	
	