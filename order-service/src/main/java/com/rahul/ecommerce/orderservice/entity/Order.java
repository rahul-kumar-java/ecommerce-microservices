package com.rahul.ecommerce.orderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Long productId;
	
	@Column(nullable=false)
	private BigDecimal totalPrice;
	
	@Column(nullable=false)
	private Integer quantity;
	
	@Column(nullable=false)
	private Long userId;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
	 this.createdAt = LocalDateTime.now();
	}
	
	
}
