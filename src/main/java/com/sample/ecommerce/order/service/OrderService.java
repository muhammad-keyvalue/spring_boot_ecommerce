package com.sample.ecommerce.order.service;

import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.OrderItemDto;
import com.sample.ecommerce.order.exception.ObjectNotFoundException;
import com.sample.ecommerce.order.model.Customer;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.CustomerRepository;
import com.sample.ecommerce.order.repository.OrderRepository;
import com.sample.ecommerce.order.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OrderService {

  @Autowired
  OrderRepository repository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ProductRepository productRepository;

  public Order create(OrderDto orderDto) {

    if (customerRepository.findById(orderDto.getCustomerId()).isEmpty()) {
      throw new ObjectNotFoundException(Customer.class);
    }

    log.info("Creating order for customer - {}", orderDto.getCustomerId());
    Order order = createOrderfromDto(new Order(), orderDto);
    Order createdOrder = repository.save(order);
    log.info(
        "Order details : order_id - {}, total_price - {}, total_quantity - {}," 
        + " customer_id - {}, order_status - {}",
        order.getId(),
        order.getTotalPrice(),
        order.getTotalQuantity(),
        orderDto.getCustomerId(),
        order.getStatus());
    return createdOrder;
  }

  public Order createOrderfromDto(Order order, OrderDto orderDto) {
    int totalQuantity = 0;
    int totalPrice = 0;
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    Product product = new Product();
    int itemId;

    int price;
    order = order.toBuilder()
        .status((orderDto.getStatus() != null) ? orderDto.getStatus() : OrderStatus.INITIATED)
        .totalQuantity(totalQuantity)
        .totalPrice(totalPrice)
        .customer(customerRepository.findById(orderDto.getCustomerId()).get()).build();

    for (OrderItemDto item : orderDto.getOrderItems()) {
      totalQuantity += item.getQuantity();
      itemId = item.getProductId();
      product = productRepository.findById(itemId).get();
      price = product.getUnitPrice();
      totalPrice += (item.getQuantity() * price);

      OrderItem orderItem = OrderItem.builder()
          .quantity(item.getQuantity())
          .product(product)
          .order(order)
          .build();
      orderItems.add(orderItem);
    }

    order.setOrderItems(orderItems);

    return order;

  }

  public Order findOne(int id) {
    try {
      return repository.findById(id).get();
    } catch (Exception e) {
      throw new ObjectNotFoundException(Order.class);
    }

  }

  public List<Order> findAll() {
    return repository.findAll();
  }

  public void delete(int id) {
    Order order = this.findOne(id);
    repository.delete(order);
    log.info("Deleted order with order_id - {}", id);
  }

  public Order update(Integer orderId, OrderDto updateOrderDto) {
    Order order = this.findOne(orderId);
    log.info("Updating order with order_id - {} ", orderId);
    Order updatedOrder = createOrderfromDto(order, updateOrderDto);
    log.info(
        "Order details : order_id - {}, total_price - {}, total_quantity - {}," 
        + " customer_id - {}, order_status - {}",
        order.getId(),
        order.getTotalPrice(),
        order.getTotalQuantity(),
        updateOrderDto.getCustomerId(),
        order.getStatus());
    return repository.save(updatedOrder);
  }

}
