package org.delivery.account.domain.token.model

import java.time.LocalDateTime

data class TokenDto(
	val token: String? = null,
	val expiredAt: LocalDateTime? = null
)
