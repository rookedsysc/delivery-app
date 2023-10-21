package org.delivery.api.domain.token.service;

import java.util.HashMap;
import java.util.Objects;

import org.delivery.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.interfaces.TokenHelperInterface;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
  private final TokenHelperInterface tokenHelperInterface;

  // 토큰 발급
  public TokenDto issueAccessToken(Long userId) {
    var data = new HashMap<String, Object>();
    data.put("userId", userId);
    return tokenHelperInterface.issueAccessToken(data);
  }

  // 리프레쉬 토큰 발급
  public TokenDto issueRefreshToken(Long userId) {
    var data = new HashMap<String, Object>();
    data.put("userId", userId);
    return tokenHelperInterface.issueRefreshToken(data);
  }

  // 토큰 to User ID
  public Long validationToken(String token) {
    var map = tokenHelperInterface.validationTokenWithThrow(token);
    System.out.println(map.keySet());
    var userId = map.get("userId");

    Objects.requireNonNull(userId, ()-> {
      throw new ApiException(ErrorCode.NULL_POINT);
    });

    return Long.parseLong(userId.toString());
  }
}
