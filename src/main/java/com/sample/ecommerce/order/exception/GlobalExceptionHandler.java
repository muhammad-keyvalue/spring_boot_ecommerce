package com.sample.ecommerce.order.exception;

import com.sample.ecommerce.order.exception.dto.ErrorResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public @ResponseBody ResponseEntity<ErrorResponse>
    handleCommonException(CommonException ex) {
        log.error(ex.getStatusCode() + ":" + ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                ex.getStatusCode().value(), ex.getMessage(), ex.getErrorCode()));
    }

}

