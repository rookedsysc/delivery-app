package org.delivery.account.domain.token.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

data class TokenDto(
	val token: String? = null,
	val expiredAt: LocalDateTime? = null
)
