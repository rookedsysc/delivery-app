package org.delivery.api.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeInterface {
  OK(200, 200, "OK"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
  NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point error"),
  ;
  
  /// HTTP 상태 코드
  private final Integer httpStatusCode;
  /// 서비스 에러 코드(개발자 정의)
  private final Integer errorCode; 
  private final String description;

  @Override
  public Integer getHttpStatusCode() {
    return this.httpStatusCode;
  }
  @Override
  public Integer getErrorCode() {
    return this.errorCode;
  }
  @Override
  public String getDescription() {
    return this.description;
  }

}
