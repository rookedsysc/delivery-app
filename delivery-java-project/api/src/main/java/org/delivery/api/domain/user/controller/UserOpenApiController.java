package org.delivery.api.domain.user.controller;

import javax.validation.Valid;

import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@RestController
@RequestMapping("/open-api")
public class UserOpenApiController {
  private final UserBusiness userBusiness;

  // 사용자 가입 요청
  @PostMapping("/register")
  public Api<UserResponse> register(UserRegisterRequest request) {
    var response = userBusiness.register(request);

    return Api.OK(response);
  }

  @PostMapping("/login")
  public Api<UserResponse> login(
    @Valid @RequestBody UserLoginRequest request
  ) {
    var response = userBusiness.login(request);

    return Api.OK(response);

  }
}
