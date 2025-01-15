package com.project.ecommercep.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommercep.entities.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cart c WHERE c.customer.id = :customerId")
    boolean existsByCustomerId(@Param("customerId") Long customerId);
}
