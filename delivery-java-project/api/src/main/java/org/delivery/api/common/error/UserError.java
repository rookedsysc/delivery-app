package org.delivery.api.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * User의 경우 1000번대 에러 사용
 */

@AllArgsConstructor
// Getter를 달아줌으로써 ErrorCodeInterfcace에서 구현해야할 것들을 구현 안해줘도 된다.
@Getter
public enum UserError implements ErrorCodeInterface {
  USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없음"),
  ;
  
  /// HTTP 상태 코드
  private final Integer httpStatusCode;
  /// 서비스 에러 코드(개발자 정의)
  private final Integer errorCode; 
  private final String description;
}
