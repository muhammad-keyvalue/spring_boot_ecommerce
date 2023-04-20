package com.sample.ecommerce.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.model.Orders;
import com.sample.ecommerce.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order/v1/orders")
public class OrderController {

  @Autowired
  OrderService service;

  /*
   * To check health, we can use actuator which provides the endpoint http://localhost:8080/actuator/health
   */

   @PostMapping()
   public Orders create(@Valid @RequestBody OrderDto createOrder){
    return service.create(createOrder);
   }

   @GetMapping("/{id}")
   public Orders findOne(@PathVariable int id){
    return service.findOne(id);
   }
   @GetMapping()
   public List<Orders> findAll(){
    return service.findAll();
   }

   @PutMapping("/{id}")
   public Orders update(@PathVariable int id, @Valid @RequestBody OrderDto updateOrder){
    return service.update(id,updateOrder);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable int id){
    service.delete(id);
   }
   
   }
   
