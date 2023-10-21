package org.delivery.api.domain.store.controller;

import javax.validation.Valid;

import org.delivery.common.api.Api;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/store")
public class StoreOpenApiController {
  private final StoreBusiness storeBusiness;
  @PostMapping("/register")
  public Api<StoreResponse> register(
    @Valid 
    @RequestBody
    StoreRegisterRequest request
    ) {
      var response = storeBusiness.register(request);

      return Api.OK(response);
    }
}
