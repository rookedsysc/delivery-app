package org.delivery.api.common.api;

import javax.validation.Valid;

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

  public static <T> Api<T> ok(T data) {
    var api = new Api<T>();
    api.result = Result.ok();
    api.body = data;
    return api;
  }
}
