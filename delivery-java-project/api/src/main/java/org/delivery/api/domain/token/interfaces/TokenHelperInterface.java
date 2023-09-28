package org.delivery.api.domain.token.interfaces;

import java.util.Map;

import org.delivery.api.domain.token.model.TokenDto;

public interface TokenHelperInterface {
  TokenDto issueAccessToken(Map<String, Object> data);
  TokenDto issueRefreshToken(Map<String, Object> data);

  Map<String, Object> validationTokenWithThrow(String token);
}
