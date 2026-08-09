package com.rahul.ecommerce.userservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.ecommerce.userservice.entity.RefreshToken;
import com.rahul.ecommerce.userservice.entity.User;
import com.rahul.ecommerce.userservice.exception.InvalidRefreshTokenException;
import com.rahul.ecommerce.userservice.repository.RefreshTokenRepository;
import com.rahul.ecommerce.userservice.security.JwtService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private static final int REFRESH_TOKEN_VALIDITY_DAYS = 7;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;

	@Override
	public String createRefreshToken(Long id) {

		RefreshToken refreshToken = RefreshToken.builder().userId(id).token(UUID.randomUUID().toString())
				.expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY_DAYS)).build();
		RefreshToken refreshTokenEntity = refreshTokenRepository.save(refreshToken);
		return refreshTokenEntity.getToken();
	}

	@Override
	public RefreshToken findByToken(String token) {

		return refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new InvalidRefreshTokenException("Invalid Refresh Token"));
	}

	@Override
	public void validateRefreshToken(RefreshToken refreshToken) {

		if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			refreshTokenRepository.delete(refreshToken);
			throw new RuntimeException("Refresh Token has expired");
		}
	}
}


















