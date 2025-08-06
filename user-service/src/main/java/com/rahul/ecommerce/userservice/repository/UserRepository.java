package com.rahul.ecommerce.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
}
