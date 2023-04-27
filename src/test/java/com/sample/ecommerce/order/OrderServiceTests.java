package com.sample.ecommerce.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.OrderItemDto;
import com.sample.ecommerce.order.exception.ObjectNotFoundException;
import com.sample.ecommerce.order.model.Category;
import com.sample.ecommerce.order.model.Customer;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.CustomerRepository;
import com.sample.ecommerce.order.repository.OrderRepository;
import com.sample.ecommerce.order.repository.ProductRepository;
import com.sample.ecommerce.order.service.OrderService;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTests {

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private ProductRepository productRepository;

  @MockBean
  private CustomerRepository customerRepository;

  @Autowired
  OrderService orderService;

  @Test
  @DisplayName("Should be able to get order by id")
  void getOrderByIdTest() {
    int randomId = 2;
    final int totalPrice = 20;
    final int quantity = 4;
    Order order = Order.builder()
        .id(randomId)
        .totalQuantity(quantity)
        .totalPrice(totalPrice)
        .build();

    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Order obtainedOrder = orderService.findOne(1);
    assertEquals(obtainedOrder, order);
  }

  @Test
  @DisplayName("Should be able to create an order")
  void createdOrderTest(){
    final int randomId = 5;
    final int customerId =1;
    final int productId = 2;
    final int totalPrice = 20;
    final int totalQuantity = 4;

    Mockito.when(customerRepository.findById(customerId))
    .thenReturn(Optional.of(getSampleCustomer(customerId)));
    Mockito.when(productRepository.findById(productId))
    .thenReturn(Optional.of(getSampleProduct(productId)));

    Order order = Order.builder()
    .id(randomId)
    .customer(getSampleCustomer(customerId))
    .status(OrderStatus.INITIATED)
    .totalQuantity(totalQuantity)
    .totalPrice(totalPrice)
    .build();

    List<OrderItem> orderItems = getSampleOrderItems(order,productId,totalQuantity);
    order.setOrderItems(orderItems);

    OrderDto orderDto = OrderDto.builder()
                        .customerId(customerId)
                        .status(OrderStatus.INITIATED)
                        .orderItems(getSampleOrderItemsDto(productId,totalQuantity))
                        .build();

    Mockito.when(orderRepository.save(any())).thenReturn(order);
    Order obtainedOrder = orderService.create(orderDto);
    assertEquals(obtainedOrder, order);
}

@Test
  @DisplayName("Should fail on creating order with invalid product")
  void failOrderOnInvalidProductTest(){

    Mockito.when(customerRepository.findById(anyInt()))
    .thenReturn(Optional.of(getSampleCustomer(1)));
    Mockito.when(productRepository.findById(anyInt()))
    .thenThrow(new ObjectNotFoundException(Product.class));

    OrderDto orderDto = OrderDto.builder()
                        .customerId(1)
                        .status(OrderStatus.INITIATED)
                        .orderItems(getSampleOrderItemsDto(1,4))
                        .build();

    assertThrows(ObjectNotFoundException.class,() -> orderService.create(orderDto));
  }

private Customer getSampleCustomer(int customerId){
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

private OrderItemDto[] getSampleOrderItemsDto(int productId,int quantity){
  OrderItemDto[] orderItems = new OrderItemDto[1] ;
  orderItems[0]= OrderItemDto.builder()
          .productId(productId)
          .quantity(quantity)
          .build();
  return orderItems;
}

private Product getSampleProduct(int productId){
return Product.builder()
      .name("Mobile")
      .image("http//:ughuogwxsjhg.com")
      .description(("Samsung Galaxy A7"))
      .unitPrice(5)
      .category(getSampleCategory())
      .build();
}

private Category getSampleCategory(){
  return Category.builder()
        .name("Electronics")
        .build();
}

private List<OrderItem> getSampleOrderItems(Order order,int productId, int quantity){
  List<OrderItem> orderItems = new ArrayList<OrderItem>();
  orderItems.add(OrderItem.builder().order(order).quantity(quantity).product(getSampleProduct(productId)).build());
return orderItems;
}

}