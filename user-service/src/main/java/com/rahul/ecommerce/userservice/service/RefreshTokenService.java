package com.rahul.ecommerce.userservice.service;

import com.rahul.ecommerce.userservice.entity.RefreshToken;

public interface RefreshTokenService {

	String createRefreshToken(Long id);

	RefreshToken findByToken(String token);

	void validateRefreshToken(RefreshToken refreshToken);
	

}
