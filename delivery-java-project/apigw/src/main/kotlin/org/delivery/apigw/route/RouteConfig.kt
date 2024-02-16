package org.delivery.apigw.route

import org.delivery.apigw.filter.ServiceApiPrivateFilter
import org.delivery.apigw.filter.ServiceApiPublicFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
        private val serviceApiPrivateFilter: ServiceApiPrivateFilter,
        private val serviceApiPublicFilter: ServiceApiPublicFilter
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder
            .routes()
            .route { spec ->
                // 우선순위
                spec.order(-1)
                spec
                        .path("/service-api/api/**") // 매칭할 주소
                        .filters { filterSpec ->
                            filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.config())) // 필터 지정
                            filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                        }
                        .uri(
                                "http://localhost:8080" // 라우팅할 주소
                        )
            }
            .route { spec ->
                spec
                        .path("/service-api/open-api/**") // 매칭할 주소
                        .filters { filterSpec ->
                            filterSpec.filter(serviceApiPublicFilter.apply(ServiceApiPublicFilter.config())) // 필터 지정
                            filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                        }
                        .uri(
                                "http://localhost:8080" // 라우팅할 주소
                        )
            }
            .build()
    }
}