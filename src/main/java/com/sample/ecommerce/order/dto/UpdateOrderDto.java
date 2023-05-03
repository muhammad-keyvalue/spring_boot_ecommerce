package com.sample.ecommerce.order.dto;

import com.sample.ecommerce.order.constants.OrderStatus;
import jakarta.validation.Valid;
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
public class UpdateOrderDto {

  private OrderStatus status;

  @Valid
  private OrderItemDto[] orderItems;
}
