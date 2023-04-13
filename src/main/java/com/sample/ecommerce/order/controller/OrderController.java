package com.sample.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sample.ecommerce.order.service.OrderService;

@RestController
public class OrderController {

  @Autowired
  OrderService service;
  
}
