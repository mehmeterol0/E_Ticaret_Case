package com.project.ecommercep.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommercep.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String productName);
}
