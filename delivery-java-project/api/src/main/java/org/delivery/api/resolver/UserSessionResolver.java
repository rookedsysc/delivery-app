package org.delivery.api.resolver;

import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {
  private final UserService userService;

  @Override
  /// 여기서 True로 return이 되면 resolveArgument가 실행됨
  public boolean supportsParameter(MethodParameter parameter) {
    // 지원하는 파라미터 체크, 어노테이션 체크하는 영역
    // 1. 어노테이션이 있는지 체크
    var annotation = parameter.hasParameterAnnotation(UserSession.class);
    // 2. parameter type 체크
    boolean parameterType = parameter.getParameterType().equals(User.class);

    return annotation && parameterType;
  }

  @Override
  @Nullable
  public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
    // support parameter가 true일 때 실행되는 영역

    // request holder에서 찾아옴
    var requestContext = RequestContextHolder.getRequestAttributes();

    var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

    var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

    // 사용자 정보 세팅
    return User.builder().id(userEntity.getId()).name(userEntity.getName()).email(userEntity.getEmail())
        .password(userEntity.getPassword()).status(userEntity.getStatus()).address(userEntity.getAddress())
        .registeredAt(userEntity.getRegisteredAt()).unregisteredAt(userEntity.getUnregisteredAt())
        .lastLoginAt(userEntity.getLastLoginAt()).build();
  }
}
