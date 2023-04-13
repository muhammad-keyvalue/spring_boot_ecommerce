package com.sample.ecommerce.order.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class product {

  @Id
  @GeneratedValue()
  private Integer id;

  private String name;

  private String categoryId;

  private String image;

  private String description;

  private String unitPrice;
  
}
