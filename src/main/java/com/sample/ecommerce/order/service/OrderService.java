package com.sample.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.grammars.hql.HqlParser.IsEmptyPredicateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.OrderItemDto;
import com.sample.ecommerce.order.model.Orders;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.CustomerRepository;
import com.sample.ecommerce.order.repository.OrderRepository;
import com.sample.ecommerce.order.repository.ProductRepository;

@Service
public class OrderService {

  @Autowired
  OrderRepository repository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ProductRepository productRepository;


  public Orders create(OrderDto orderDto) {
    Orders newOrder= new Orders();
    return repository.save(createOrderfromDto(newOrder,orderDto));
  }


  public Orders createOrderfromDto(Orders order,OrderDto orderDto) {
    int totalQuantity = 0;
    int totalPrice = 0;
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    Product product = new Product();

    int itemId, price;

    for(OrderItemDto item : orderDto.getOrderItems()){
      totalQuantity+=item.getQuantity();
      itemId = item.getProductId();
      product = productRepository.findById(itemId).get();
      price = product.getUnitPrice();
      totalPrice +=(item.getQuantity() * price);
    
   
    OrderItem orderItem = new OrderItem();
    orderItem.setQuantity(item.getQuantity());
    orderItem.setProduct(product);
    orderItem.setOrder(order);
    orderItems.add(orderItem);
    }

    if(orderDto.getStatus()== null)
      order.setStatus(OrderStatus.INITIATED);
    else
      order.setStatus(orderDto.getStatus());
    order.setTotalQuantity(totalQuantity);
    order.setTotalPrice(totalPrice);
    order.setOrderItems(orderItems);
    order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());

    return order;

  }

  public Orders findOne(int id) {
    return repository.findById(id).get();
  }

  public List<Orders> findAll() {
    return repository.findAll();
  }

  public void delete(int id) {
    repository.deleteById(id);
  }

  public Orders update(Integer orderId, OrderDto updateOrderDto) {
    Orders order = repository.findById(orderId).get();
    Orders updatedOrder= createOrderfromDto(order, updateOrderDto);
    return repository.save(updatedOrder);
  }

 
}
