package org.delivery.db.storemenu;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {
  @Column(nullable = false)
  private Long storeId;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(precision = 11, scale = 4, nullable = false)
  private BigDecimal amount;

  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreMenuStatus status;

  @Column(length = 500, nullable = false)
  private String thumbnailUrl;

  // Null값이 들어가지 않기 때문에 primitive type으로 선언
  private int likeCount;

  private int sequence;
}
