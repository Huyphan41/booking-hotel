package com.example.demo.application.dto;

import com.example.demo.application.exception.DisplayException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class StatusMessage {
  private final ErrorCode code;
  private final String message;
  private final List<String> params;

  private StatusMessage(ErrorCode code, String message, List<String> params) {
    this.code = code;
    this.message = message;
    this.params = params;
  }

  private StatusMessage(ErrorCode code, String message) {
    this(code, message, Collections.emptyList());
  }

  public static StatusMessage of(int code, String message, List<String> params) {
    return ErrorCode.fromErrorCode(code)
        .map(errorCode -> new StatusMessage(errorCode, message, params))
        .orElseThrow(() -> new DisplayException(
            new StatusMessage(ErrorCode.INVALID_ERROR_CODE, String.format("Invalid Error Code: [%s] ", code))));
  }

  public static StatusMessage of(int code, String message) {
    return of(code, message, Collections.emptyList());
  }

  public static StatusMessage of(ErrorCode errorCode, String... messageParams) {
    return new StatusMessage(errorCode, errorCode.getMessageDisplayKey(), Arrays.asList(messageParams));
  }

  public boolean isSuccess() {
    return code == ErrorCode.SUCCESS;
  }
}
