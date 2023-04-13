package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class order {
  
  @Id
  @GeneratedValue()
  private Integer id;  

  private Integer customerId;

  private String status;

  private String createdAt;

  private String updatedAt;

}
