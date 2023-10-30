package org.delivery.api.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.delivery.common.error.ErrorCode;
import org.delivery.common.error.TokenError;
import org.delivery.common.error.UserError;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
  private final TokenBusiness TokenBusiness;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("Authorization Interceptor url : {}", request.getRequestURI());
    
    // Chrome의 경우 OPTIONS 메소드를 먼저 호출해서 GET이나 POST등 메서드를 지원하는지 먼저 확인한다.
    // 그걸 통과하는 로직
    if(HttpMethod.OPTIONS.matches(request.getMethod())) {
      return true;
    }

    // js, html, png를 요청하는 경우 Pass
    if(handler instanceof ResourceHttpRequestHandler) {
      return true;
    }

    // TODO: Header 검증 (여기에 인증 넣을 예정)
    var userId = request.getHeader("x-user-id");
    if(userId == null) {
      throw new ApiException(ErrorCode.BAD_REQUEST, "유저 아이디가 없습니다.");
    }

    // 인증 성공
    var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
    requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
    return true;
  }
}
