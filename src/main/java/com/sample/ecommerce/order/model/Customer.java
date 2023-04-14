package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {
 
  @Id
  @GeneratedValue()
  private Integer id;   
   
  private String name;

  private String phone;

  private String email;

  private String street;

  private String city;

  private String state;

  private String profilePic;

  private Date createdAt;

  private String countryCode;

  @OneToMany(mappedBy = "customer")
  private List<Order> orders;

}
