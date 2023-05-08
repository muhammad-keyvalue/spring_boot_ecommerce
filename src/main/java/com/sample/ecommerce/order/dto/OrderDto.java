package com.sample.ecommerce.order.dto;

import com.sample.ecommerce.order.constants.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

  @NotNull
  private Integer customerId;
 
  private OrderStatus status;
  
  @NotEmpty
  @Valid
  private OrderItemDto[] orderItems;
}
