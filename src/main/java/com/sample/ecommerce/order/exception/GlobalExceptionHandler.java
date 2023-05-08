package com.sample.ecommerce.order.exception;

import com.sample.ecommerce.order.exception.dto.ErrorResponse;
import com.sample.ecommerce.order.util.ResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = CommonException.class)
  public @ResponseBody ResponseEntity<ResponseDto> handleCommonException(CommonException ex) {
    log.error(ex.getStatusCode() + ":" + ex.getMessage());
    ErrorResponse errorResponse = new ErrorResponse(
         ex.getStatusCode().value(), ex.getMessage(), ex.getErrorCode());
    return ResponseEntity.status(ex.getStatusCode())
    .body(new ResponseDto(null, errorResponse, null));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @ResponseBody ResponseEntity<ResponseDto> 
  handleValidationErrors(MethodArgumentNotValidException ex) {
    List<String> errors = new ArrayList<String>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse(
         HttpStatus.BAD_REQUEST.value(), errors.toString(), ExceptionCodes.VALIDATION_FAILED);

    return ResponseEntity.status(ex.getStatusCode())
          .body(new  ResponseDto(null, errorResponse, null));

  }


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseDto> handleTypeMismatchErrors(HttpMessageNotReadableException ex) {
    List<String> errors = new ArrayList<String>();
    errors.add(ex.getMessage());
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ExceptionCodes.TYPE_MISMATCH);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    .body(new  ResponseDto(null, errorResponse, null));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleInternalServerErrorException(Exception ex) {
    List<String> errors = new ArrayList<String>();
    errors.add(ex.getMessage());
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), 
        ExceptionCodes.INTERNAL_SERVER_ERROR);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(new  ResponseDto(null, errorResponse, null));

  }

}

