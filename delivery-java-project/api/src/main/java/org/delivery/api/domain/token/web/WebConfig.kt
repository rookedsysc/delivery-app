package org.delivery.api.domain.token.web

import org.delivery.api.interceptor.AuthorizationInterceptor
import org.delivery.api.resolver.UserSessionResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
	private val authorizationInterceptor: AuthorizationInterceptor,
	private val userSessionResolver: UserSessionResolver
): WebMvcConfigurer {
	private val OPEN_API = listOf(
		"/open-api/**"
	)
	
	private val DEFAULT_EXCLUDE = listOf(
		"/", "/favicon.ico", "/error"
	)
	
	private val SWAGGER =
		listOf("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**")
	
	override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
		resolvers.add(userSessionResolver)
	}
}