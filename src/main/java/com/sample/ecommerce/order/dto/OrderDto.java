package com.sample.ecommerce.order.dto;

import com.sample.ecommerce.order.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
