package org.delivery.api.config.health

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping("/open-api")
class HealthOpenController{
	private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
	@GetMapping("/health")
	fun health() {
		logger.info("health call")
	}
}