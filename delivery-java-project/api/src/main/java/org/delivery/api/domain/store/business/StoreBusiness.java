package org.delivery.api.domain.store.business;

import java.util.List;
import java.util.stream.Collectors;

import org.delivery.common.annotation.Business;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreRegisterConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.enums.StoreCategory;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class StoreBusiness {
  private final StoreRegisterConverter registerConverter;
  private final StoreService storeService;

  public StoreResponse register(
    StoreRegisterRequest request
  ) {
    var entity = registerConverter.toEntity(request);
    var newEntity = storeService.register(entity);
    var response = registerConverter.toResponse(newEntity);
    return response;
  }

  public List<StoreResponse> searchCategory(StoreCategory storeCategory) {
    // entity list -> response list
    var storeList = storeService.searchByCategory(storeCategory);

    return storeList.stream().map(registerConverter::toResponse).collect(Collectors.toList());
  }
}
