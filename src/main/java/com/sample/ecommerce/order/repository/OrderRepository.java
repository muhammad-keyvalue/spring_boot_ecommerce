package com.sample.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.ecommerce.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
