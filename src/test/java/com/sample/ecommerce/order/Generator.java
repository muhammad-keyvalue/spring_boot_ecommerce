package com.sample.ecommerce.order;


import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.OrderItemDto;
import com.sample.ecommerce.order.model.Category;
import com.sample.ecommerce.order.model.Customer;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Generator {
  
  public static Customer getSampleCustomer(int customerId) {
    return Customer.builder()
        .id(customerId)
        .name("Manu")
        .phone("+91934345")
        .email("manukk@gmail.com")
        .street("acd")
        .state("asd")
        .profilePic("http://imageurl")
        .countryCode("KWI")
        .build();

  }

  public static OrderDto getSampleOrderDto(int productId, int quantity) {
    return OrderDto.builder()
    .customerId(1)
    .status(OrderStatus.INITIATED)
    .orderItems(Generator.getSampleOrderItemsDto(productId, quantity))
    .build();
  }

  public static OrderItemDto[] getSampleOrderItemsDto(int productId, int quantity) {
    OrderItemDto[] orderItems = new OrderItemDto[1];
    orderItems[0] = OrderItemDto.builder()
        .productId(productId)
        .quantity(quantity)
        .build();
    return orderItems;
  }

  public static Product getSampleProduct(int productId) {
    return Product.builder()
        .name("Mobile")
        .image("http//:ughuogwxsjhg.com")
        .description(("Samsung Galaxy A7"))
        .unitPrice(5)
        .category(getSampleCategory())
        .build();
  }

  public static Category getSampleCategory() {
    return Category.builder()
        .name("Electronics")
        .build();
  }

  public static Order getSampleOrder(int id, int totalQuantity, int totalPrice) {
    return Order.builder()
        .id(id)
        .customer(Generator.getSampleCustomer(1))
        .status(OrderStatus.INITIATED)
        .totalQuantity(totalQuantity)
        .totalPrice(totalPrice)
        .build();

  }

  public static List<OrderItem> getSampleOrderItems(Order order, int productId, int quantity) {
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(OrderItem.builder()
        .order(order).quantity(quantity).product(getSampleProduct(productId)).build());
    return orderItems;
  }

  
}
