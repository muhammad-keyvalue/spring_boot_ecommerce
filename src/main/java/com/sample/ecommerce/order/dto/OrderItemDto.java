package com.sample.ecommerce.order.dto;


import jakarta.validation.constraints.NotNull;
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
  
  @NotNull
  private Integer productId;

  @NotNull
  @Min(1)
  private Integer quantity;
}
