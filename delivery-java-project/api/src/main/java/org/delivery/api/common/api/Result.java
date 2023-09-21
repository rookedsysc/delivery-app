package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Result {
  private Integer resultCode;
  private String resultMessage;
  private String resultDescription;

  public static Result ok() {
    return Result.builder()
        .resultCode(200)
        .resultMessage("OK")
        .resultDescription("The request was successful.")
        .build();
  }
}
