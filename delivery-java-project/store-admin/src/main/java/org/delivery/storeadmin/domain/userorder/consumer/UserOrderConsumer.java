package org.delivery.storeadmin.domain.userorder.consumer;

import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserOrderConsumer {
  @RabbitListener(queues = "delivery.queue")
  public void consume(
      UserOrderMessage userOrderMessage
  ) {
    log.info("userOrderMessage : {}", userOrderMessage);
  }
}
