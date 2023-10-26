package org.delivery.account.domain.token.controller

import org.delivery.account.common.Log
import org.delivery.account.domain.token.business.TokenBusiness
import org.delivery.account.domain.token.controller.model.TokenValidationRequest
import org.delivery.account.domain.token.controller.model.TokenValidationResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/private-api/token")
class TokenPrivateApiController (
	private val tokenBusiness: TokenBusiness
){
	companion object: Log
	
	@PostMapping("/validation")
	fun tokenValidation(
		@RequestBody
		tokenValidationRequest: TokenValidationRequest?
	): TokenValidationResponse? {
		log.info("token validation init : $tokenValidationRequest}")
		return tokenBusiness.tokenValidation(tokenValidationRequest)
	
	}
}