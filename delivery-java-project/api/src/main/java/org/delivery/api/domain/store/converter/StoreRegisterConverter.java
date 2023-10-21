package org.delivery.api.domain.store.converter;

import java.util.Optional;

import org.delivery.api.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.StoreEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class StoreRegisterConverter {
  public StoreEntity toEntity(StoreRegisterRequest request) {
    return StoreEntity.builder()
        .name(request.getName())
        .address(request.getAddress())
        .category(request.getCategory())
        .thumbnailUrl(request.getThumbnailUrl())
        .minimumAmount(request.getMinimumAmount())
        .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
        .phoneNumber(request.getPhoneNumber())
        .build();
  }

  public StoreResponse toResponse(StoreEntity entity) {
    return Optional.ofNullable(entity).map((it) -> {
      return StoreResponse.builder()
          .id(it.getId())
          .name(it.getName())
          .address(it.getAddress())
          .storeStatus(it.getStatus())
          .storeCategory(it.getCategory())
          .star(it.getStar())
          .thumbnailUrl(it.getThumbnailUrl())
          .minimumAmount(it.getMinimumAmount())
          .minimumDeliveryAmount(it.getMinimumDeliveryAmount())
          .phoneNumber(it.getPhoneNumber())
          .build();
    }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }
}
