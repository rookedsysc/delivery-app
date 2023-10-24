package com.example.apigw.filter

import com.example.apigw.common.Log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class ServiceApiPublicFilter: AbstractGatewayFilterFactory<ServiceApiPublicFilter.config>(config::class.java) {
	
	companion object: Log
	
	class config
	
	override fun apply(config: config): GatewayFilter {
		return GatewayFilter { exchange, chain ->
			var uri = exchange.request.uri
			
			log.info("ServiceApiFilter uri: $uri")
			
			val mono = chain.filter(exchange)
			
			mono
		}
		
	}
}