package com.rahul.ecommerce.userservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.userservice.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByToken(String token);

	
}
