package org.delivery.api.domain.userorder.controller.model

import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse

class UserOrderDetailResponse(
	val userOrderResponse: UserOrderResponse? = null,
	val storeResponse: StoreResponse? = null,
	val storeMenuResponseList: List<StoreMenuResponse>? = null
)