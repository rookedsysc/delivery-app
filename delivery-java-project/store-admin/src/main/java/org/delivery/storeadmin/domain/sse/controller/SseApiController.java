package org.delivery.storeadmin.domain.sse.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

  /// Thread Safe한 자료구조
  /// TODO: ConcurrentHashMap에 대해 공부
  private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();

  @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseBodyEmitter connect(@Parameter(hidden = true) String userId, @AuthenticationPrincipal UserSession userSesssion) {
    log.info("login user {}", userSesssion);

    /// milisecond 단위로 timeout 시간 설정가능
    var emitter = new SseEmitter();
    userConnection.put(userSesssion.getUserId()
        .toString(), emitter);

    /// Default Timeout: 30s
    emitter.onTimeout(() -> {
      log.info("on timeout");
      emitter.complete();
    });

    /// 연결이 종료될 때(timeout 등으로) 해줘야 하는 작업
    emitter.onCompletion(() -> {
      log.info("on completion");
      /// connection 제거
      userConnection.remove(userSesssion.getUserId().toString());
    });

    /// 최초 연결시 응답 전송
    var event = SseEmitter.event()
        .name("connect")
        .data("connected");
    try {
      emitter.send(event);
    } catch (IOException e) {
      emitter.completeWithError(e);
    }

    return emitter;
  }

  @GetMapping("/push-event")
  public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
    /// 기존에 연결된 유저 찾기
    var emitter = userConnection.get(userSession.getUserId()
        .toString());

    var event = SseEmitter.event()
        .data("hello"); // onmessage

    try {
      emitter.send(event);
    } catch (IOException e) {
      emitter.completeWithError(e);
    }
  }
}
