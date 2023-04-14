package com.sample.ecommerce.order.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

  @Id
  @GeneratedValue()
  private Integer id;

  private String name;

  private String image;

  private String description;

  private String unitPrice;

  @OneToMany(mappedBy = "product")
  private List<OrderItem> orderItems;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;


  
}
