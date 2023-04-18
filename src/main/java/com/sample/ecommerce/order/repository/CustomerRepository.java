package com.sample.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.ecommerce.order.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
  
}
