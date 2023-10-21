package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StoreRepository : JpaRepository<StoreEntity, Long> {
	// 유효한 스토어인지 검증 후 유효하다면 스토어 정보를 가져온다. 
	// select * from store where id = ? and status = "REGISTERED" order by id desc limit 1;
	fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?
	
	// 유효한 스토어 리스트 
	// select * from store where status = "REGISTERED" order by id desc;
	fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>
	
	// 특정 카테고리 
	fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>
	
	// select * from store where name = ? and status = ? order by id desc limit 1
	fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?
}
