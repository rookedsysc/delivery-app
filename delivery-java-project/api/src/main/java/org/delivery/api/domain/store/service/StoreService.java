package org.delivery.api.domain.store.service;

import java.util.List;
import java.util.Optional;

import org.delivery.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
  private final StoreRepository storeRepository;

  // 유효한 스토어 가져오기
  public StoreEntity getStoreWithThrow(Long id) {
    var storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    return storeEntity;
  }

  // 스토어 등록
  public StoreEntity register(StoreEntity storeEntity) {
    return Optional.ofNullable(storeEntity).map(it -> {
      it.setStar(0);
      it.setStatus(StoreStatus.REGISTERED);
      return storeRepository.save(it);
    }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 카테고리로 스토어 검색
  public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {
    var list = storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory);
    return list;
  }

  // 전체 스토어
  public List<StoreEntity> allRegisterStore() {
    var list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    return list;
  }
}
