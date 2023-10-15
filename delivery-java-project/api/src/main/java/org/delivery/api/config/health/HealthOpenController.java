package org.delivery.api.config.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.rabbitmq.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
public class HealthOpenController {
  private final Producer producer;
  @GetMapping("health")
  public void health() {
    log.info("health check");
    producer.send("delivery.exchange", "delivery.routeKey", "health check");
  }
}
