package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.ErrorCodeInterface

class Result(
	val resultCode: Int,
	val resultMessage: String,
	val resultDescription: String
) {
	companion object {
		fun ok(): Result {
			return Result(ErrorCode.OK.getErrorCode(), ErrorCode.OK.getDescription(), "The request was successful.")
		}
		
		fun ERROR(errorCode: ErrorCodeInterface): Result {
			return Result(
				resultCode = errorCode.getErrorCode(),
				resultDescription = errorCode.getDescription(),
				resultMessage = "The request was failed."
			)
		}
		
		fun ERROR(errorCode: ErrorCodeInterface, throwable: Throwable): Result {
			return Result(
				resultCode = errorCode.getErrorCode(),
				resultDescription = throwable.localizedMessage,
				resultMessage = errorCode.getDescription()
			)
		}
		
		fun ERROR(errorCode: ErrorCodeInterface, description: String): Result {
			return Result(
				resultCode = errorCode.getErrorCode(),
				resultDescription = description,
				resultMessage = errorCode.getDescription()
			)
		}
	}
}