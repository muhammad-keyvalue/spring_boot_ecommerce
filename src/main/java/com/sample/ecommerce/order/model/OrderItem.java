package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;  

  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonBackReference
  private Orders order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonManagedReference
  private Product product;

  
}
