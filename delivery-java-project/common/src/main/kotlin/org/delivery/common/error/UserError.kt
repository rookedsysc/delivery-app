package org.delivery.common.error

import lombok.Getter

/**
 * User의 경우 1000번대 에러 사용
 */
enum class UserError (
	private val httpStatusCode: Int,
	private val errorCode: Int,
	private val description: String
): ErrorCodeInterface {
  USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없음"),
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