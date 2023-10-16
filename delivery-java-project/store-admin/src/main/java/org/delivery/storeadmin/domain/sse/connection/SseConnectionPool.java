package org.delivery.storeadmin.domain.sse.connection;

import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
/// UserSseConnection은 매번 new로 생성되는 객체이고
/// SseConnectionPool은 Spring이 관리하는 하나만 존재하는 static 객체임
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSseConnection> {
  private final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

  @Override
  public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
    connectionPool.put(uniqueKey, userSseConnection);
  }

  @Override
  public UserSseConnection getSession(String uniqueKey) {
    return connectionPool.get(uniqueKey);
  }

  @Override
  public void onCompletionCallback(UserSseConnection session) {
    connectionPool.remove(session.getUniqueKey());
  }
}

