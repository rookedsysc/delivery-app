package org.delivery.api.domain.user.business;

import java.util.Optional;

import org.delivery.common.annotation.Business;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {
  private final UserService userService;
  private final UserConverter userConverter;
  private final TokenBusiness tokenBusiness;

  public UserResponse register(UserRegisterRequest request) {
    return Optional.ofNullable(request).map(userConverter::toEntity).map(userService::register)
        .map(userConverter::toResponse).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Reqeust Null"));
  }

  /**
   * 1. email, password를 받아서 사용자 체크
   * 2. user entity 로그인 확인
   * 3. 토큰 생성
   * 4. 토큰 리턴
   */
  public TokenResponse login(
      UserLoginRequest request) {
    var userEntity = userService.login(request.getEmail(), request.getPassword());

    var tokenResponse = tokenBusiness.issueToken(userEntity);

    return tokenResponse;
  }

  public UserResponse me(Long userId) {
    var userEntity = userService.getUserWithThrow(userId);
    var response = userConverter.toResponse(userEntity);

    return response;
  }
}
