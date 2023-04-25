package com.sample.ecommerce.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

  public class ObjectNotFoundException  extends CommonException {
 
  private static final long serialVersionUID = 1L;

    public <T> ObjectNotFoundException(Class<T> entity) {
        super(HttpStatus.NOT_FOUND, ExceptionCodes.OBJECT_NOT_FOUND, entity.getSimpleName() + " not found");
    }

    public <T> ObjectNotFoundException(Class<T> entity, String simpleName) {
      super(HttpStatus.NOT_FOUND, ExceptionCodes.OBJECT_NOT_FOUND, simpleName + " not found");
  }
}
