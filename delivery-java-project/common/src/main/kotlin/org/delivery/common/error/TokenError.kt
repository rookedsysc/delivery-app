package org.delivery.common.error

/**
 * Token의 경우 2000번대 에러 사용
 */
enum class TokenError (
	private val httpStatusCode: Int,
	private val errorCode: Int,
	private val description: String
): ErrorCodeInterface {
  INVALID_TOKEN(400, 2400, "유효하지 않은 토큰"),
  EXPIRED_TOKEN(400, 2401, "만료된 토큰"),
  TOEKN_EXCEPTION(400, 2402, "토큰 예외 발생"),
  AUTHROIZATION_HEADER_NOT_FOUND(400, 2403, "Authorization Header가 존재하지 않습니다."),
	;
	
	override fun getHttpStatusCode(): Int {
		return this.httpStatusCode
	}
	
	override fun getErrorCode(): Int {
		return this.errorCode
	}
	
	override fun getDescription(): String {
		return this.description
	}
}