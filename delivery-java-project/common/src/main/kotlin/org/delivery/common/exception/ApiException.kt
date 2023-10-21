package org.delivery.common.exception

import lombok.Getter
import org.delivery.common.error.ErrorCodeInterface

class ApiException :
	RuntimeException, ApiExceptionInterface {
	override val errorCodeInterface: ErrorCodeInterface?
	override val errorDescription: String?
	
	constructor(errorCodeInterface: ErrorCodeInterface) : super(errorCodeInterface.getDescription()) {
		this.errorCodeInterface = errorCodeInterface
		this.errorDescription = errorCodeInterface.getDescription()
	}
	
	constructor(errorCodeInterface: ErrorCodeInterface, errorCodeDescription: String) : super(errorCodeDescription) {
		this.errorCodeInterface = errorCodeInterface
		this.errorDescription = errorCodeDescription
	}
	
	constructor(
		errorCodeInteface: ErrorCodeInterface,
		throwable: Throwable
	) : super(throwable) {
		this.errorCodeInterface = errorCodeInteface
		this.errorDescription = errorCodeInteface.getDescription()
	}
	
	constructor(
		errorCodeInteface: ErrorCodeInterface,
		throwable: Throwable,
		errorCodeDescription: String
	) : super(throwable) {
		this.errorCodeInterface = errorCodeInteface
		this.errorDescription = errorCodeDescription
	}
}