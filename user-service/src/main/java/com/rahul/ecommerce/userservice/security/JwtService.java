package com.rahul.ecommerce.userservice.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	// Base64 encoded secret key (minimum 32 characters before encoding)
	private static final String secret = "myverysecuresecretkeyforjwt123456";
	// plain text: mysecretkeymysecretkeymysecretkey123
	
	
	  // Generate Token
    public String generateToken(String username) {

        return Jwts.builder()

                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(getSignKey())
                .compact();
    }
    
    // Extract Username
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }
	
	
    // Validate Token
    public boolean isTokenValid(String token, String username) {

        final String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }
	
	
    // Check Expiration
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
	
	
    // Extract Claims (NEW API)
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    // Signing Key
    private SecretKey getSignKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }
	
}






















