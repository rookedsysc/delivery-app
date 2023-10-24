package org.delivery.account.domain.token.service

import org.delivery.account.domain.token.ifs.TokenHelperIfs
import org.delivery.account.domain.token.model.TokenDto
import org.springframework.stereotype.Service

@Service
class TokenService(private val tokenHelper: TokenHelperIfs) {
	fun issueAccessToken(userId: Long?): TokenDto? {
		return userId?.let {
			val data = mapOf("userId" to it)
			tokenHelper.issueAccessToken(data)
		}
	}
	
	fun issueRefreshToken(userId: Long?): TokenDto? {
		/// requiredNotNull 구문이 나오고 그 이후는
		/// 그 안에 들어간 데이터가 NotNull임을 의미하고 Null일 경우 에러 발생
		requireNotNull(userId)
		val data = mapOf("userId" to userId)
		return tokenHelper.issueRefreshToken(data)
	}
	
	fun validationTokenWithThrow(token: String?): Long? {
//		 requireNotNull(token)
//		 val map = tokenHelper.validationTokenWithThrow(token)
//		 val userId = map?.get("userId")
//		 requireNotNull(userId)
//		 return userId.toString().toLong()
		
		return tokenHelper
			.validationTokenWithThrow(token)
			?.let { map ->
				map["userId"]
			}.toString().toLong()
	}
}