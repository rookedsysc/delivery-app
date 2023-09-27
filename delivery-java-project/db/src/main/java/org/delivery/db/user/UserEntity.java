package org.delivery.db.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.delivery.db.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// 여기에 name = "user" 이런식으로 사용해도 됨
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
public class UserEntity  extends BaseEntity{
  @Column(nullable = false, length = 50)
  private String name;
  @Column(nullable = false, length = 100)
  private String email;
  @Column(nullable = false, length = 100)
  private String password;
  @Column(nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private UserStatus status;
  @Column(length = 150, nullable = false)
  private String address;
  private LocalDateTime registeredAt;
  private LocalDateTime unregisteredAt;
  private LocalDateTime lastLoginAt;
}
