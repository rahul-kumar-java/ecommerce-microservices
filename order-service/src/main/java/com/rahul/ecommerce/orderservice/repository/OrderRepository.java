package com.rahul.ecommerce.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
