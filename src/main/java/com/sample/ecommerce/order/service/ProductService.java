package com.sample.ecommerce.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository repository;

  public List<Product> getProducts(List<Integer> productList) {

    return repository.findByIdIn(productList);
  }
}
