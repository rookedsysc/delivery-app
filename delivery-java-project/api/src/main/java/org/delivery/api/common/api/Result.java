package org.delivery.api.common.api;

import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeInterface;

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
        .resultCode(ErrorCode.OK.getErrorCode())
        .resultMessage(ErrorCode.OK.getDescription())
        .resultDescription("The request was successful.")
        .build();
  }

  public static Result ERROR(ErrorCodeInterface errorCodeInterface) {
    return Result.builder()
        .resultCode(errorCodeInterface.getErrorCode())
        .resultMessage(errorCodeInterface.getDescription())
        .resultDescription("The request was unsuccessful.")
        .build();
  }

  public static Result ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
    return Result.builder()
        .resultCode(errorCodeInterface.getErrorCode())
        .resultMessage(errorCodeInterface.getDescription())
        .resultDescription(tx.getLocalizedMessage())
        .build();
  }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, String description) {
    return Result.builder()
        .resultCode(errorCodeInterface.getErrorCode())
        .resultMessage(errorCodeInterface.getDescription())
        .resultDescription(description)
        .build();
  }
}
