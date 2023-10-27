package org.delivery.account.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.delivery.account.domain.token.ifs.TokenHelperIfs
import org.delivery.account.domain.token.model.TokenDto
import org.delivery.common.error.TokenError
import org.delivery.common.exception.ApiException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtTokenHelper
	: TokenHelperIfs {
	// Spring.bean.factory.annotation.Value
	@Value("\${token.secret.key}")
	private val secretKey: String? = null
	
	@Value("\${token.access-token.plus-hour}")
	private val accessTokenPlushHour: Long = 1
	
	@Value("\${token.refresh-token.plus-hour}")
	private val refreshTokenPlusHour: Long = 12
	
	override fun issueAccessToken(data: Map<String, Any>?): TokenDto? {
		val expiredAtLocalDateTime = LocalDateTime
			.now()
			.plusHours(accessTokenPlushHour)
		
		val expiredAt = Date.from(
			expiredAtLocalDateTime
				.atZone(ZoneId.systemDefault())
				.toInstant()
		)
		
		val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())
		
		val jwtToken = Jwts
			.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(data)
			.setExpiration(expiredAt)
			.compact()
		
		return TokenDto(jwtToken, expiredAtLocalDateTime)
	}
	
	override fun issueRefreshToken(data: Map<String, Any>?): TokenDto? {
		val expiredAtLocalDateTime = LocalDateTime
			.now()
			.plusHours(refreshTokenPlusHour)
		
		val expiredAt = Date.from(
			expiredAtLocalDateTime
				.atZone(ZoneId.systemDefault())
				.toInstant()
		)
		
		val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())
		
		val jwtToken = Jwts
			.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(data)
			.setExpiration(expiredAt)
			.compact()
		
		return TokenDto(jwtToken, expiredAtLocalDateTime)
	}
	
	override fun validationTokenWithThrow(token: String?): Map<String, Any>? {
		val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())
		
		val parser = Jwts
			.parserBuilder()
			.setSigningKey(key)
			.build()
		
		return try {
			val result = token?.let { parser.parseClaimsJws(it) }
			HashMap(result?.body)
		} catch (e: Exception) {
			when (e) {
				is SignatureException -> {
					throw ApiException(TokenError.INVALID_TOKEN)
				}
				
				is ExpiredJwtException -> {
					throw ApiException(TokenError.EXPIRED_TOKEN)
				}
				
				else -> {
					throw ApiException(TokenError.TOEKN_EXCEPTION, e)
				}
			}
		}
	}
}