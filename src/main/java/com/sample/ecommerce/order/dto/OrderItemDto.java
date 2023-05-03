package com.sample.ecommerce.order.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
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
public class OrderItemDto {
  
  @Nonnull
  private int productId;

  @Nonnull
  @Min(1)
  private int quantity;
}
