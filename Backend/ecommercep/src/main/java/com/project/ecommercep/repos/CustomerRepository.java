package com.project.ecommercep.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommercep.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {

}
