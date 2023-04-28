package com.sample.ecommerce.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@Entity
public class OrderItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;  

  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonBackReference
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonManagedReference
  private Product product;

  
}
