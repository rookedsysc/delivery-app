package org.delivery.api.exceptionHandler;

import org.delivery.common.api.Api;
import org.delivery.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class ApiExceptionHandler {
  @ExceptionHandler(value =  ApiException.class)
  public ResponseEntity<Api<Object>> apiException(
    ApiException apiException
  ) {
    log.error("", apiException);

    var errorCode = apiException.getErrorCodeInterface();

    return ResponseEntity.status(errorCode.getHttpStatusCode())
        .body(Api.ERROR(errorCode, apiException.getErrorDescription()));
  }
}
