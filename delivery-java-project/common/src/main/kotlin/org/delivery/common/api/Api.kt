package org.delivery.common.api


data class Api<T> (
	val result: Result? = null,
	val body: T? = null,
	)