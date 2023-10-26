package org.delivery.apigw.intercepter

import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.util.Loggers

val webClient = WebClient
	.builder()
	.baseUrl("http://locahost") // 원하는 베이스 URL로 설정
	.filter(logRequest()) // 요청 로깅 필터 추가
	.filter(logResponse()) // 응답 로깅 필터 추가
	.build()

fun logRequest(): ExchangeFilterFunction {
	return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
		val logger = Loggers.getLogger("request.logger")
		logger.info("Request: ${clientRequest.method()} ${clientRequest.url()}")
		// 다른 request 관련 정보를 로깅하려면 clientRequest 객체를 사용
		Mono.just(clientRequest)
	}
}

fun logResponse(): ExchangeFilterFunction {
	return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
		val logger = Loggers.getLogger("response.logger")
		logger.info("Response status: ${clientResponse.rawStatusCode()}")
		// 다른 response 관련 정보를 로깅하려면 clientResponse 객체를 사용
		Mono.just(clientResponse)
	}
}
