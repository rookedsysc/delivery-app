package org.delivery.api.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Bean
  /// Exchnage 생성
  public DirectExchange directExchange() {
    return new DirectExchange("delivery.exchange");
  }

  @Bean
  /// Queue 생성
  public Queue queue() {
    return new Queue("delivery.queue");
  }

  @Bean
  /// Excnage랑 Queue를 delivery.key로 binding
  public Binding binding(DirectExchange directExchange, Queue queue) {
    return BindingBuilder.bind(queue)
        .to(directExchange)
        .with("delivery.key");
  }

  @Bean
  /// RabbitTemplate : Object <-> Json 해주는 역할
  public RabbitTemplate rabbitTemplate(
      /// rabbit.connection.ConnectionFactory
      /// application.yaml 파일에 작성
      ConnectionFactory connectionFactory,
      /// amqp.support.converter.MessageConverter
      MessageConverter messageConverter
  ) {

    var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    return rabbitTemplate;
  }

  @Bean
  /// Spring이 Bean으로 등록되어 있는 ObjectMapper를 사용
  public MessageConverter messageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
