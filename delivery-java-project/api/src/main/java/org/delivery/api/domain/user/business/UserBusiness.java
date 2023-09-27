package org.delivery.api.domain.user.business;

import java.util.Optional;

import javax.validation.Valid;

import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
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

  /*
   * 사용자에 대한 가입 처리 로직
   * 1. reques
   */
  public UserResponse register(UserRegisterRequest request) {
    // var entity = userConverter.toEntity(request);
    // var newEntity = userService.register(entity);
    // var response = userConverter.toResponse(newEntity);

    // return response;

    return Optional.ofNullable(request).map(userConverter::toEntity).map(userService::register)
        .map(userConverter::toResponse).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Reqeust Null"));
  }

  /**
   * 1. email, password를 받아서 사용자 체크
   * 2. user entity 로그인 확인
   * 3. 토큰 생성
   * 4. 토큰 리턴
   */
  public UserResponse login(
      UserLoginRequest request) {
    var userEntity = userService.login(request.getEmail(), request.getPassword());

    // 사용자가 없으면 Throw

    // TODO: 토큰 생성

    return userConverter.toResponse(userEntity);
  }
}
