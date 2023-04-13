package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class orderItem {
  
  @Id
  @GeneratedValue()
  private Integer id;  

  private String orderId;

  private String productId;

  private String quantity;
  
}
