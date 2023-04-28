package com.sample.ecommerce.order.repository;

import com.sample.ecommerce.order.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
