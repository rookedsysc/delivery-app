package org.delivery.api.domain.user.model;

import java.time.LocalDateTime;

import lombok.*;
import org.delivery.db.user.enums.UserStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User {
  private Long id;
  private String name;
  private String email;
  private String password;
  private UserStatus status;
  private String address;
  private LocalDateTime registeredAt;
  private LocalDateTime unregisteredAt;
  private LocalDateTime lastLoginAt;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public UserStatus getStatus() {
    return status;
  }

  public String getAddress() {
    return address;
  }

  public LocalDateTime getRegisteredAt() {
    return registeredAt;
  }

  public LocalDateTime getUnregisteredAt() {
    return unregisteredAt;
  }

  public LocalDateTime getLastLoginAt() {
    return lastLoginAt;
  }
}
