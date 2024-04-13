package com.example.demo.application.dto;

import java.util.Optional;

public enum ErrorCode {
  SUCCESS(0, 0,"SUCCESS"),
  INVALID_REQUEST(1000, 403, "INVALID_REQUEST"),
  INVALID_ERROR_CODE(1001, 0, "INVALID_ERROR_CODE"),
  INTERNAL_ERROR(-1, 500, "INTERNAL_ERROR");

  private final int errorCode;
  private final int statusCode;
  private final String messageDisplayKey;

  private ErrorCode(int errorCode, int statusCode, String messageDisplayKey) {
    this.errorCode = errorCode;
    this.messageDisplayKey = messageDisplayKey;
    this.statusCode = statusCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getMessageDisplayKey() {
    return messageDisplayKey;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public static Optional<ErrorCode> fromErrorCode(int errorCode) {
    for (ErrorCode errorCodeItem : ErrorCode.values()) {
      if (errorCodeItem.errorCode == errorCode) {
        return Optional.of(errorCodeItem);
      }
    }
    return Optional.empty();
  }
}
