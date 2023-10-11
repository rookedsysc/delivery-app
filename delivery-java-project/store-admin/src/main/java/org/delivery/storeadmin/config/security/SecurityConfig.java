package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private List<String> SWAGGER = List.of("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**");


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // Default는 활성화
        .csrf()
        .disable()
        .authorizeHttpRequests(it -> {
          // static Resource에 대해서는 모두 허가함
          it.requestMatchers(
                  PathRequest.toStaticResources()
                      .atCommonLocations()
              )
              .permitAll()
              // swagger는 인증 없이 통과
              .mvcMatchers(SWAGGER.toArray(new String[0]))
              .permitAll()
              // open-api 예외
              .mvcMatchers("/open-api/**")
              .permitAll()
              // 그 외 모든 요청은 인증을 받음
              .anyRequest()
              .authenticated();
        })
        .formLogin(Customizer.withDefaults());
    return httpSecurity.build();
  }
}
