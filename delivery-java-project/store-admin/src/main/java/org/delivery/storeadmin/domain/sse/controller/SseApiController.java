package org.delivery.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

  /// Thread Safe한 자료구조
  /// TODO: ConcurrentHashMap에 대해 공부
  private final SseConnectionPool sseConnectionPool;
  private final ObjectMapper objectMapper;

  @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseBodyEmitter connect(
      @Parameter(hidden = true) String userId,
      @AuthenticationPrincipal UserSession userSesssion
  ) {
    log.info("login user {}", userSesssion);

    var userSseConnection = UserSseConnection.connect(userSesssion.getStoreId()
        .toString(), sseConnectionPool, objectMapper);

    sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

    return userSseConnection.getSseEmitter();
  }

  @GetMapping("/push-event")
  public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
    var userSseConnection = sseConnectionPool.getSession(userSession.getStoreId()
        .toString());

    Optional.ofNullable(userSseConnection)
        .ifPresent(it -> {
          it.sendMessage("hello world");
        });
  }
}
