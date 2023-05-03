package com.sample.ecommerce.order.dto;

import com.sample.ecommerce.order.constants.OrderStatus;
import jakarta.annotation.Nonnull;
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
public class OrderDto {

  @Nonnull
  private Integer customerId;
 
  private OrderStatus status;

  @Nonnull
  @Valid
  private OrderItemDto[] orderItems;
}
