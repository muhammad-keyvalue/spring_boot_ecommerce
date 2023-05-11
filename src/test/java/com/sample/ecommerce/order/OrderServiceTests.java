package com.sample.ecommerce.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import com.sample.ecommerce.order.constants.OrderStatus;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.UpdateOrderDto;
import com.sample.ecommerce.order.exception.ObjectNotFoundException;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.model.OrderItem;
import com.sample.ecommerce.order.model.Product;
import com.sample.ecommerce.order.repository.CustomerRepository;
import com.sample.ecommerce.order.repository.OrderRepository;
import com.sample.ecommerce.order.repository.ProductRepository;
import com.sample.ecommerce.order.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test for order service.
 */

@SpringBootTest()
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

  final int randomId = 1;
  final int orderId = 1;
  final int customerId = 1;
  final int productId = 1;
  final int quantity = 2;
  final int totalQuantity = 4;
  final int totalPrice = 40;

  @Test
  @DisplayName("Should be able to get order by id")
  void getOrderByIdTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Order obtainedOrder = orderService.findOne(orderId);
    assertEquals(obtainedOrder, order);
  }

  @Test
  @DisplayName("Should fail get-order-by-id on providing non existing order id")
  void failGetOrderByIdOnInvalidIdTest() {
    Mockito.when(orderRepository.findById(anyInt()))
        .thenThrow(new ObjectNotFoundException(Order.class));
    assertThrows(ObjectNotFoundException.class, () -> orderService.findOne(orderId));

  }

  @Test
  @DisplayName("Should be able to create an order")
  void createdOrderTest() {
    Mockito.when(customerRepository.findById(any()))
        .thenReturn(Optional.of(Generator.getSampleCustomer(customerId)));
    Mockito.when(productRepository.findById(any()))
        .thenReturn(Optional.of(Generator.getSampleProduct(productId)));

    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);

    List<OrderItem> orderItems = Generator.getSampleOrderItems(order, productId, quantity);
    order.setOrderItems(orderItems);

    OrderDto orderDto = Generator.getSampleOrderDto(productId, quantity);

    Mockito.when(orderRepository.save(any())).thenReturn(order);
    Order obtainedOrder = orderService.create(orderDto);
    assertEquals(obtainedOrder, order);
  }

  @Test
  @DisplayName("Should fail on creating order with invalid product")
  void failOrderOnInvalidProductTest() {

    Mockito.when(customerRepository.findById(anyInt()))
        .thenReturn(Optional.of(Generator.getSampleCustomer(customerId)));
    Mockito.when(productRepository.findById(anyInt()))
        .thenThrow(new ObjectNotFoundException(Product.class));

    OrderDto orderDto = Generator.getSampleOrderDto(productId, quantity);

    assertThrows(ObjectNotFoundException.class, () -> orderService.create(orderDto));
  }

  @Test
  @DisplayName("Should fail on creating order with invalid customer")
  void failOrderOnInvalidCustomerTest() {

    Mockito.when(customerRepository.findById(anyInt()))
        .thenReturn(Optional.empty());
    Mockito.when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(Generator.getSampleProduct(productId)));

    OrderDto orderDto = Generator.getSampleOrderDto(productId, quantity);

    assertThrows(ObjectNotFoundException.class, () -> orderService.create(orderDto));
  }

  @Test
  @DisplayName("Should be able to delete order by id ")
  void deleteOrderTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    assertEquals("Deleted order with order_id - " + randomId, orderService.delete(randomId));
  }

  @Test
  @DisplayName("Should fail on deleting non existing order")
  void failDeleteOnInvalidOrderTest() {
    Mockito.when(orderRepository.findById(anyInt()))
        .thenThrow(new ObjectNotFoundException(Order.class));
    assertThrows(ObjectNotFoundException.class, () -> orderService.delete(randomId));
  }

  @Test
  @SuppressWarnings("unchecked")
  @DisplayName("Should get all orders")
  void getAllOrderTest() {
    final int page = 0;
    final int size = 1;
    List<Order> orders = new ArrayList<Order>();
    Mockito.when(orderRepository.findAll())
        .thenReturn(orders);
    assertEquals(orders, orderService.findAll());

    PageRequest pageable = PageRequest.of(page, size, Sort.unsorted());
    Page<Order> pagedOrder = Mockito.mock(Page.class);
    Mockito.when(orderRepository.findAll(pageable)).thenReturn(pagedOrder);
    assertEquals(pagedOrder, orderService.findAll(page, size, Sort.unsorted()));
  }

  @Test
  @DisplayName("Should update order with given id ")
  void updateOrderTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    Mockito.when(orderRepository.save(any())).thenReturn(order);
    Mockito.when(orderRepository.findById(any()))
        .thenReturn(Optional.of(order));
    Mockito.when(customerRepository.findById(any()))
        .thenReturn(Optional.of(Generator.getSampleCustomer(customerId)));
    Mockito.when(productRepository.findById(any()))
        .thenReturn(Optional.of(Generator.getSampleProduct(productId)));
    UpdateOrderDto dto = UpdateOrderDto.builder()
        .orderItems(Generator.getSampleOrderItemsDto(productId, quantity))
        .status(OrderStatus.INITIATED)
        .build();

    assertEquals(order, orderService.update(orderId, dto));
  }

  @Test
  @DisplayName("Should fail on updating invalid order")
  void failUpdateOnInvalidOrderIdTest() {
    Mockito.when(orderRepository.findById(anyInt()))
        .thenThrow(new ObjectNotFoundException(Order.class));
    UpdateOrderDto dto = new UpdateOrderDto();
    assertThrows(ObjectNotFoundException.class, () -> orderService.update(orderId, dto));
  }

}