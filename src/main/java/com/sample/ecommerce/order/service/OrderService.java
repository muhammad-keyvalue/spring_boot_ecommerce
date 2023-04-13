package com.sample.ecommerce.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.ecommerce.order.repository.OrderRepository;

@Service
public class OrderService {
  
  @Autowired
  OrderRepository repository;
}
