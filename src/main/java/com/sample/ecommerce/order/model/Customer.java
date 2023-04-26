package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;   
   
  private String name;

  private String phone;

  private String email;

  private String street;

  private String city;

  private String state;

  private String profilePic;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private String countryCode;

  @OneToMany(mappedBy = "customer")
  @JsonBackReference
  private List<Orders> orders;

}
