package com.sample.ecommerce.order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Date;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class customer {
 
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

}
