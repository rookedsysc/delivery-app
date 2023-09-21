package org.delivery.api.common.api;

import javax.validation.Valid;

import org.delivery.api.common.error.ErrorCodeInterface;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {
  private Result result;
  @Valid
  private T body;

  public static <T> Api<T> OK(T data) {
    var api = new Api<T>();
    api.result = Result.ok();
    api.body = data;
    return api;
  }

  public static Api<Object> ERROR(Result result) {
    var api = new Api();
    api.result = result;
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface) {
    var api = new Api();
    api.result = Result.ERROR(errorCodeInterface);
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
    var api = new Api();
    api.result = Result.ERROR(errorCodeInterface, tx);
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, String description) {
    var api = new Api();
    api.result = Result.ERROR(errorCodeInterface, description);
    return api;
  }
}
