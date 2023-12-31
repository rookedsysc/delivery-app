package org.delivery.account.domain.token.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.delivery.account.domain.token.model.TokenDto

data class TokenValidationRequest(
	val tokenDto: TokenDto? = null,
)
