 package com.sample.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.sample.ecommerce.order.constants.OrderStatus;

import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
 
  private Integer customerId;

  private OrderStatus status;

  private OrderItemDto[] orderItems;
}
