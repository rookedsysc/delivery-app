package org.delivery.apigw.filter

import org.delivery.apigw.common.Log
import org.delivery.apigw.domain.account.controller.model.TokenValidationRequest
import org.delivery.apigw.domain.account.controller.model.TokenValidationResponse
import org.delivery.apigw.domain.account.model.TokenDto
import org.delivery.common.error.TokenError
import org.delivery.common.exception.ApiException
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Component
class ServiceApiPrivateFilter: AbstractGatewayFilterFactory<ServiceApiPrivateFilter.config>(config::class.java) {
	
	companion object : Log
	
	class config
	
	override fun apply(config: config): GatewayFilter {
		return GatewayFilter { exchange, chain ->
			/// 사용자의 요청이 들어옴
			var uri = exchange.request.uri
			log.info("ServiceApiFilter uri: $uri")
			
			/// account server를 통한 인증 실행
			/// 1. 토큰의 유무
			val headers = exchange.request.headers["authorization-token"]?: listOf()
			
			val token = if(headers.isEmpty()) {
				throw ApiException(TokenError.AUTHROIZATION_HEADER_NOT_FOUND)
			} else {
				headers.get(0)
			}
			
			log.info("authorization-token: $token")
			
			/// 2. 토큰의 유효성
			val accountApiUrl = UriComponentsBuilder
				.fromUriString("http://localhost")
				.port("8082")
				.path("/private-api/token/validation")
				.build()
				.encode()
				.toString()
			val webClient = WebClient
				.builder()
				.baseUrl(accountApiUrl)
				.build()
			val request = TokenValidationRequest(
				tokenDto = TokenDto(
					token = token
				)
			)
			
			/// Netty는 WebFlux를 쓰고 있기 때문에 RestTemplate이 아니라 WebClient를 사용한다.
			/// 아래 코드는 WebClient를 사용해서 account 서버에 post 요청을 보내는 코드
			webClient
				.post()
				.body(Mono.just(request), object : ParameterizedTypeReference<TokenValidationRequest>() {})
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(
					{ status: HttpStatus ->
						status.isError
					},
					{ response: ClientResponse ->
						response
							.bodyToMono(object : ParameterizedTypeReference<Any>() {})
							.flatMap { error ->
								log.error("error: $error")
								Mono.error(ApiException(TokenError.TOEKN_EXCEPTION))
							}
					}
				)
				/// Account 서버에서 토큰 인증이 성공하고 응답이 왓을 때
				.bodyToMono(object : ParameterizedTypeReference<TokenValidationResponse>() {})
				.flatMap { response ->
					log.info("response : $response")
					
					/// 3. 사용자 정보 추가
					val userId = response.userId?.toString()
					
					/// 프록시에 userId를 추가함
					val proxyRequest = exchange.request.mutate()
						.header("X-USER-ID", userId)
						.build()
					
					val requestBuild = exchange.mutate()
						.request(proxyRequest)
						.build()
					
					/// chain 필터에 요청을 보냄
					val mono = chain.filter(exchange)
					mono
				}
				.onErrorMap {
					e -> log.error("error : $e")
					e
				}
		}
	}
}