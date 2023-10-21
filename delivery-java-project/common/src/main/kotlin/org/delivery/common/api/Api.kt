package org.delivery.common.api

import org.delivery.common.error.ErrorCodeInterface
import javax.validation.Valid


data class Api<T>(
	val result: Result? = null,
	/// Kotlin에서는 변수에 Annotation을 붙이기 위해선
	/// @field:Annotation 형태로 붙여야 한다.
	@field:Valid
	val body: T? = null,
) {
	companion object {
		@JvmStatic
		fun <T> OK(body: T?): Api<T> {
			return Api(
				result = Result.ok(),
				body = body
			)
		}
		
		@JvmStatic
		fun <T> ERROR(result: Result): Api<T> {
			return Api(
				result = result,
			)
		}
		
		@JvmStatic
		fun <T> ERROR(errorCodeInterface: ErrorCodeInterface): Api<T> {
			return Api(
				result = Result.ERROR(errorCodeInterface),
			)
		}
		
		@JvmStatic
		fun <T> ERROR(errorCodeInterface: ErrorCodeInterface, throwable: Throwable): Api<T> {
			return Api(
				result = Result.ERROR(errorCodeInterface, throwable),
			)
		}
		
		@JvmStatic
		fun <T> ERROR(errorCodeInterface: ErrorCodeInterface, description: String): Api<T> {
			return Api(
				result = Result.ERROR(errorCodeInterface, description),
			)
		}
	}
}