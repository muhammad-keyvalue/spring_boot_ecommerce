package com.sample.ecommerce.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.ecommerce.order.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
  
  List<Product> findByIdIn(List<Integer> productIdList);
}

