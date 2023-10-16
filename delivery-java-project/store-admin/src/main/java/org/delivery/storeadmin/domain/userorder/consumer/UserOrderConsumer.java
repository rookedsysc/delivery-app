package org.delivery.storeadmin.domain.userorder.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.storeadmin.domain.userorder.business.UserOrderBusiness;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserOrderConsumer {
  private final UserOrderBusiness userOrderBusiness;

  @RabbitListener(queues = "delivery.queue")
  public void consume(
      UserOrderMessage userOrderMessage
  ) {
    log.info("userOrderMessage : {}", userOrderMessage);

    try {
      userOrderBusiness.pushUserOrder(userOrderMessage);
    } catch (Exception e) {
      log.error("consume error : {}", e.getMessage());
    }
  }
}
