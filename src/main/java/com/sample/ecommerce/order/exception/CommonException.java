package com.sample.ecommerce.order.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
  private HttpStatus statusCode;
  private String errorCode;
  private String message;
}
