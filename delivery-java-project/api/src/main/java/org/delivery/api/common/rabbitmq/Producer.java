package org.delivery.api.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
  private final RabbitTemplate rabbitTemplate;
  public void send(String exchange, String routekey, Object object) {
    rabbitTemplate.convertAndSend(exchange, routekey, object);
  }
}
