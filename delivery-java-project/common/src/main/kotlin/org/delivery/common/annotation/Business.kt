package org.delivery.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Service
annotation class Business(
	/// get Method만 지원하기 때문에 @get: Annotation을 사용한다.
	/// Kotlin에서 Java의 클래스를 가져올 때는 ::class를 통해서 가져온다
	@get:AliasFor(annotation = Service::class)
	val value: String = "",
)
