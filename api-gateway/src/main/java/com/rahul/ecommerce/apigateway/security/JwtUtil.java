package com.rahul.ecommerce.apigateway.security;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	// Base64 encoded secret key (minimum 32 characters before encoding)
		private static final String SECRET = "myverysecuresecretkeyforjwt123456";
		// plain text: mysecretkeymysecretkeymysecretkey123
		
		private SecretKey getSignKey() {
	        return Keys.hmacShaKeyFor(
	                SECRET.getBytes());
	    }
		
		public Claims validateToken(String token) {

	        SecretKey key = getSignKey();

	        return Jwts.parser()
	                .verifyWith(key)
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
		
}













