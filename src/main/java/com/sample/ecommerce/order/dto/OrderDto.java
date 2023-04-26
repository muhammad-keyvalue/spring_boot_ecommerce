 package com.sample.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.sample.ecommerce.order.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
 
  private Integer customerId;

  private OrderStatus status;

  private OrderItemDto[] orderItems;
}
