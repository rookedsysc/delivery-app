package org.delivery.api.config.web;

import java.util.List;

import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.delivery.api.resolver.UserSessionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final AuthorizationInterceptor authorizationInterceptor;
  private final UserSessionResolver userSessionResolver;
  private List<String> OPEN_API = List.of(
      "/open-api/**");

  private List<String> DEFAULT_EXCLUDE = List.of(
      "/", "/favicon.ico", "/error");

  private List<String> SWAGGER = List.of("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs");

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authorizationInterceptor)
        .excludePathPatterns(OPEN_API).excludePathPatterns(DEFAULT_EXCLUDE).excludePathPatterns(SWAGGER);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(userSessionResolver);
  }

}
