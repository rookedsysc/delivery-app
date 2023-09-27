package org.delivery.api.domain.user.controller.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Enumerated;

import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
  private Long id;
  private String name;
  private String email;
  private UserStatus status;
  private String address;
  private LocalDateTime registeredAt;
  private LocalDateTime unregisteredAt;
  private LocalDateTime lastLoginAt;
}
