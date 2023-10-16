package org.delivery.storeadmin.domain.userordermenu.converter;

import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorderid.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.storeadmin.common.Converter;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
        UserOrderEntity userOrderEntity,
        StoreMenuEntity storeMenuEntity
    ){
        return UserOrderMenuEntity.builder()
            .userOrderId(userOrderEntity.getId())
            .storeMenuId(storeMenuEntity.getId())
            .build()
            ;
    }

}
