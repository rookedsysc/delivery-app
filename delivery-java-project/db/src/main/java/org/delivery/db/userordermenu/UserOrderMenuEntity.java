package org.delivery.db.userordermenu;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorderid.UserOrderEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order_menu")
public class UserOrderMenuEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_order_id")
    private UserOrderEntity userOrder;   // UserOrderID 1 : n UserOrderMenuEntity

    @ManyToOne
    @JoinColumn(nullable = false)
    private StoreMenuEntity storeMenu;   // 1 : n

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderMenuStatus status;
}
