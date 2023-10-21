package org.delivery.api.common.exception;

import org.delivery.common.error.ErrorCodeInterface;

public interface ApiExceptionInterface {
  ErrorCodeInterface getErrorCodeInterface();
  String getErrorDescription();
}
