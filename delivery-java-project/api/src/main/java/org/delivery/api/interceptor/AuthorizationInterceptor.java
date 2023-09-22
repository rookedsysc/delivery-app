package org.delivery.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {@Override
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
    return true;
  }
}
