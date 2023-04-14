package com.sample.ecommerce.order.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

  @Id
  @GeneratedValue()
  private Integer id;
  
  private String name;  
  
  private Integer parentCategory;  

  @OneToMany(mappedBy = "category")
  private List<Product> products
  ;
  
}
