package com.rahul.ecommerce.userservice.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {

	private Long id;
	private String name;
	private String email;
	private LocalDateTime createdAt;
	
}
