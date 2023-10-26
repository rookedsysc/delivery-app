package org.delivery.apigw.domain.account.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.delivery.apigw.domain.account.model.TokenDto


@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TokenValidationRequest(
	val tokenDto: TokenDto? = null,
)
