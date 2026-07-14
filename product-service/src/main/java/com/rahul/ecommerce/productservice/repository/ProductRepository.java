package com.rahul.ecommerce.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rahul.ecommerce.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
