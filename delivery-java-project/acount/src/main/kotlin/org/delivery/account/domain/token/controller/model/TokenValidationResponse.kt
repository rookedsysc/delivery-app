package org.delivery.account.domain.token.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class TokenValidationResponse(
	val userId: Long? = null,
)
