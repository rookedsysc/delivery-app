package org.delivery.db.store;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
  // 유효한 스토어인지 검증 후 유효하다면 스토어 정보를 가져온다. 
  // select * from store where id = ? and status = "REGISTERED" order by id desc limit 1;
  Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

  // 유효한 스토어 리스트 
  // select * from store where status = "REGISTERED" order by id desc;
  List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);


  // 특정 카테고리 
  List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory category);

  // select * from store where name = ? and status = ? order by id desc limit 1
  Optional<StoreEntity> findFirstByNameAndStatusOrderByIdDesc(String name, StoreStatus status);
}
