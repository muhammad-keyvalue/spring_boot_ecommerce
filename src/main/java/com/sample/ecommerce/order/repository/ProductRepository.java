package com.sample.ecommerce.order.repository;

import com.sample.ecommerce.order.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findByIdIn(List<Integer> productIdList);
}
