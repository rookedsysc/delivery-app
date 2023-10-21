package org.delivery.db.userorderid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.userorderid.enums.UserOrderStatus;
import org.delivery.db.userordermenu.UserOrderMenuEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order_id")
public class UserOrderEntity extends BaseEntity {

  @Column(nullable = false)
  private Long userId;    // user table 1:n

  /// StoreEntity 쪽에서는 UserOrderEntity와 연결하지 않는다.
  /// UserOrderEntity 만으로도 데이터가 많을 수 있는데
  /// userOrderMenuEntity 까지 연결이 되있어서 걷잡을 수 없이 큰 데이터가 될 수 있기 때문이다.
  @ManyToOne
  @JoinColumn(nullable = false, name = "store_id")
  private StoreEntity storeEntity;

  @Enumerated(EnumType.STRING)
  @Column(length = 50, nullable = false)
  private UserOrderStatus status;

  @Column(precision = 11, scale = 4, nullable = false)
  private BigDecimal amount;

  private LocalDateTime orderedAt;

  private LocalDateTime acceptedAt;

  private LocalDateTime cookingStartedAt;

  private LocalDateTime deliveryStartedAt;

  private LocalDateTime receivedAt;

  /// userOrderEntity 쪽과 연결
  @OneToMany(mappedBy = "userOrder")
  @ToString.Exclude
  /// ObjectMapper에서 JSON으로 변환할 때, 해당 필드를 무시하도록 설정
  @JsonIgnore
  private List<UserOrderMenuEntity> userOrderMenuList; // UserOrderMenuEntity 1 : n UserOrderMenuEntity
}
