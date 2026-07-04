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
import com.rahul.ecommerce.userservice.security.JwtService;

	@RestController
	@RequestMapping("/api/auth")
	public class AuthController {
	
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
	    private	JwtService jwtService;
		
		@PostMapping("/login")
		public AuthResponse login(@RequestBody LoginRequest loginRequest) {
			
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(),
	                loginRequest.getPassword()));
			
			 String generatedToken=jwtService.generateToken(loginRequest.getUsername());
			 System.out.println("generated token:  "+generatedToken);
			//AuthResponse generatedToken=authService.login(loginRequest);
			//System.out.println("generated token:  "+generatedToken);
			//return ResponseEntity.ok(generatedToken);
			return  new AuthResponse(generatedToken);
		}
	}
