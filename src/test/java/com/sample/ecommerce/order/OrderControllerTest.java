package com.sample.ecommerce.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import com.sample.ecommerce.order.controller.OrderController;
import com.sample.ecommerce.order.dto.OrderDto;
import com.sample.ecommerce.order.dto.UpdateOrderDto;
import com.sample.ecommerce.order.model.Order;
import com.sample.ecommerce.order.service.OrderService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test for order controller.
 */
@SpringBootTest()
@ActiveProfiles("test")
public class OrderControllerTest {

  @MockBean
  private OrderService orderService;

  @Autowired
  OrderController orderController;

  final int randomId = 1;
  final int productId = 1;
  final int quantity = 2;
  final int totalQuantity = 4;
  final int totalPrice = 40;

  @Test
  void createdOrderTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    OrderDto dto = Generator.getSampleOrderDto(productId, quantity);
    Mockito.when(orderService.create(dto)).thenReturn(order);
    assertEquals(order, orderController.create(dto));
  }

  @Test
  void updateOrderTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    UpdateOrderDto dto = UpdateOrderDto.builder()
        .orderItems(Generator.getSampleOrderItemsDto(productId, quantity))
        .build();
    Mockito.when(orderService.update(randomId, dto)).thenReturn(order);
    assertEquals(order, orderController.update(randomId, dto));
  }

  @Test
  void findAllOrderTest() {
    List<Order> orders = new ArrayList<Order>();
    Mockito.when(orderService.findAll())
        .thenReturn(orders);
    assertEquals(orders, orderController.findAll());
  }

  @Test
  @SuppressWarnings("unchecked")
  void findAllPaginatedTest() {
    final int page = 0;
    final int size = 1;
    Page<Order> pagedOrder = Mockito.mock(Page.class);
    Mockito.when(orderService.findAll(page, size, Sort.unsorted())).thenReturn(pagedOrder);
    assertEquals(pagedOrder, orderController.findAll(page, size, null));
  }

  @Test
  void findOneOrderTest() {
    Order order = Generator.getSampleOrder(randomId, totalQuantity, totalPrice);
    Mockito.when(orderService.findOne(anyInt())).thenReturn(order);
    assertEquals(order, orderController.findOne(randomId));
  }

  @Test
  void deleteOrderTest() {
    Map<String, String> message = new HashMap<String, String>();
    message.put("message", "Deleted order with order_id - " + randomId);
    Mockito.when(orderService.delete(anyInt()))
        .thenReturn("Deleted order with order_id - " + randomId);
    assertEquals(message, orderController.delete(randomId));
  }

}
