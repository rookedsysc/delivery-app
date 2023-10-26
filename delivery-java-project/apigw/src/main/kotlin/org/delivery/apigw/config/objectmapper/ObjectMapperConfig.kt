package org.delivery.apigw.config.objectmapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
//class ObjectMapperConfig {
//	@Bean
//	fun objectMapper(): ObjectMapper {
//		val kotlinModule = KotlinModule
//			.Builder()
//			.apply {
//				withReflectionCacheSize(512)
//				/// List가 들어왔을 때
//				// true => 사이즈 0인 리스트, false => null
//				configure(KotlinFeature.NullToEmptyCollection, false)
//				configure(KotlinFeature.NullToEmptyMap, true)
//				configure(KotlinFeature.NullIsSameAsDefault, false)
//				configure(KotlinFeature.SingletonSupport, false)
//				configure(KotlinFeature.StrictNullChecks, false)
//			}
//			.build()
//
//		val objectMapper = ObjectMapper().apply {
//			registerModule(Jdk8Module())
//			registerModule(JavaTimeModule())
//			registerModule(kotlinModule)
//
//			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//			configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
//
//			disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//
//			propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
//		}
//
//		return objectMapper
//	}
//}