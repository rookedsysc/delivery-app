package org.delivery.db.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // select * from user where id = ? and status = ? order by id desc limit 1
  Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

  // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
  Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, String status);
}
