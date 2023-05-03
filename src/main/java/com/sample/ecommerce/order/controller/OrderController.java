package com.sample.ecommerce.order.controller;

import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.UpdateOrderDto;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/v1/orders")
public class OrderController {

  @Autowired
  OrderService orderservice;

  /*
   * To check health, we can use actuator which provides the endpoint
   * http://localhost:8080/actuator/health
   */

  @PostMapping()
  public Order create(@Valid @RequestBody OrderDto createOrder) {
    return orderservice.create(createOrder);
  }

  @GetMapping("/{id}")
  public Order findOne(@PathVariable int id) {
    return orderservice.findOne(id);
  }

  @GetMapping("/paginated")
  public Page<Order> findAll(@RequestParam int page, @RequestParam int size,
      @RequestParam(required = false) Sort sort) {
    if (sort == null)
      sort = Sort.unsorted();
    return orderservice.findAll(page, size, sort);
  }

  @GetMapping()
  public List<Order> findAll() {
    return orderservice.findAll();
  }


  @PutMapping("/{id}")
  public Order update(@PathVariable int id, @Valid @RequestBody UpdateOrderDto updateOrder) {
    return orderservice.update(id, updateOrder);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    orderservice.delete(id);
  }

}
