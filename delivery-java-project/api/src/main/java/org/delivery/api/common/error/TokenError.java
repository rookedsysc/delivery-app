package org.delivery.api.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Token의 경우 2000번대 에러 사용
 */

@AllArgsConstructor
// Getter를 달아줌으로써 ErrorCodeInterfcace에서 구현해야할 것들을 구현 안해줘도 된다.
@Getter
public enum TokenError implements ErrorCodeInterface {
  INVALID_TOKEN(400, 2400, "유효하지 않은 토큰"),
  EXPIRED_TOKEN(400, 2401, "만료된 토큰"),
  TOEKN_EXCEPTION(400, 2402, "토큰 예외 발생"),
  AUTHROIZATION_HEADER_NOT_FOUND(400, 2403, "Authorization Header가 존재하지 않습니다."),
  ;
  
  /// HTTP 상태 코드
  private final Integer httpStatusCode;
  /// 서비스 에러 코드(개발자 정의)
  private final Integer errorCode; 
  private final String description;
}
