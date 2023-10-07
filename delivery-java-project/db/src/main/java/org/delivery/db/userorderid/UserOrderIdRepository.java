package org.delivery.db.userorderid;

import org.delivery.db.userorderid.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderIdRepository extends JpaRepository<UserOrderIdEntity, Long> {

    // 특정 유저의 모든 주문
    // select * from user_order where user_id = ? and status = ? order by id desc
    List<UserOrderIdEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);


    // select * from user_order where user_id = ? and status in (?,? .. ) order by id desc
    List<UserOrderIdEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    // 특정 주문
    // select * from user_order where id = ? and status = ? and user_id = ?
    Optional<UserOrderIdEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    Optional<UserOrderIdEntity> findAllByIdAndUserId(Long id, Long userId);
}
