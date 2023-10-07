package org.delivery.api.domain.store.controller.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.delivery.db.store.enums.StoreCategory;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreRegisterRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotNull
  private StoreCategory category;
  @NotBlank
  private String thumbnailUrl;
  @NotNull
  private BigDecimal minimumAmount;
  @NotNull
  private BigDecimal minimumDeliveryAmount;
  @NotBlank
  private String phoneNumber;
}
