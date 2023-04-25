package com.sample.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.OrderItemDto;
import com.sample.ecommerce.order.exception.ObjectNotFoundException;
import com.sample.ecommerce.order.model.Orders;
import com.sample.ecommerce.order.model.Customer;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.CustomerRepository;
import com.sample.ecommerce.order.repository.OrderRepository;
import com.sample.ecommerce.order.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderService {

  @Autowired
  OrderRepository repository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ProductRepository productRepository;


  public Orders create(OrderDto orderDto) {
  
    if(customerRepository.findById(orderDto.getCustomerId()).isEmpty())
      throw new ObjectNotFoundException(Customer.class);
  
    log.info("Creating order for customer - {}" , orderDto.getCustomerId());
    Orders order= new Orders();
    Orders createdOrder = repository.save(createOrderfromDto(order,orderDto));
    log.info("Order details : order_id - {}, total_price - {}, total_quantity - {}, customer_id - {}, order_status - {}" ,order.getId(),order.getTotalPrice(),order.getTotalQuantity(),orderDto.getCustomerId(),order.getStatus());
    return createdOrder;
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
    try{
    return repository.findById(id).get();
    }
    catch(Exception e)
    {
      throw new ObjectNotFoundException(Orders.class,"order");
    }

  }
  public List<Orders> findAll() {
    return repository.findAll();
  }

  public void delete(int id){
    Orders order = this.findOne(id);
    repository.delete(order);
    log.info("Deleted order with order_id - {}",id);
  }

  public Orders update(Integer orderId, OrderDto updateOrderDto) {
    Orders order = this.findOne(orderId);
    log.info("Updating order with order_id - {} ",orderId);
    Orders updatedOrder= createOrderfromDto(order, updateOrderDto);
    log.info("Order details : order_id - {}, total_price - {}, total_quantity - {}, customer_id - {}, order_status - {}" ,order.getId(),order.getTotalPrice(),order.getTotalQuantity(),order.getCustomer().getId(),order.getStatus());
    return repository.save(updatedOrder);
  }

 
}
