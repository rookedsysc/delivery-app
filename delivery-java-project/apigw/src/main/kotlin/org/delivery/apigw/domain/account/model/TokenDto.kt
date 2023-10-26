package org.delivery.apigw.domain.account.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TokenDto(
	val token: String? = null,
	val expiredAt: LocalDateTime? = null
)
