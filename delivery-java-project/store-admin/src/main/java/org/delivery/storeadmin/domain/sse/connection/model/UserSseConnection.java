package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserSseConnection {
  private final String uniqueKey;
  private final SseEmitter sseEmitter;
  private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;
  private final ObjectMapper objectMapper;

  public static UserSseConnection connect(
      String uniqueKey,
      ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
      ObjectMapper objectMapper
  ) {
    return new UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper);
  }

  private UserSseConnection(
      String uniqueKey,
      ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
      ObjectMapper objectMapper
  ) {
    this.uniqueKey = uniqueKey;
    this.connectionPoolIfs = connectionPoolIfs;
    this.objectMapper = objectMapper;

    this.sseEmitter = new SseEmitter(60 * 1000L);

    this.sseEmitter.onCompletion(() -> {
      this.connectionPoolIfs.onCompletionCallback(this);
    });

    this.sseEmitter.onTimeout(() -> {
      this.sseEmitter.complete();
    });

    /// sse가 초기화되면 반드시 onopen 메시지를 처리해야함
    sendMessage("onopen", "connected");
  }

  public void sendMessage(String eventName, Object data) {

    try {
      var json = this.objectMapper.writeValueAsString(data);
      var event = SseEmitter.event()
          .name(eventName)
          .data(json);
      this.sseEmitter.send(event);
    } catch (IOException e) {
      this.sseEmitter.completeWithError(e);
    }
  }

  public void sendMessage(Object data) {

    try {
      var json = this.objectMapper.writeValueAsString(data);
      var event = SseEmitter.event()
          .data(json);
      this.sseEmitter.send(event);
    } catch (IOException e) {
      this.sseEmitter.completeWithError(e);
    }
  }

}
