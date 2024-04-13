package com.example.demo.application.dto;

import java.util.Collections;
import java.util.List;

public class ResultDto<T> {
  private StatusMessage statusMessage;
  private T results;

  private ResultDto(StatusMessage statusMessage, T results) {
    this(statusMessage);
    this.results = results;
  }

  private ResultDto(StatusMessage statusMessage) {
    this.statusMessage = statusMessage;
  }

  public static <T> ResultDto<T> fail(StatusMessage statusMessage) {
    return new ResultDto<>(statusMessage);
  }

  public static <T> ResultDto<T> success(T results) {
    return new ResultDto<>(StatusMessage.of(ErrorCode.SUCCESS.getErrorCode(), ""), results);
  }

  public static <T> ResultDto<T> of(ErrorCode errorCode, String message) {
    return new ResultDto<>(StatusMessage.of(errorCode.getErrorCode(), message));
  }

  public static <T> ResultDto<T> of(StatusMessage statusMessage, T results) {
    return new ResultDto<>(statusMessage, results);
  }

  public StatusMessage getStatusMessage() {
    return statusMessage;
  }

  public T getResults() {
    return results;
  }

  public static <T> Builder<T> newBuilder() {
    return new Builder<>();
  }

  public static class Builder<T> {

    private StatusMessage statusMessage;
    private T results;

    public Builder() {

    }

    public Builder<T> withSuccess(String message) {
      return withSuccess(message, Collections.emptyList());
    }

    public Builder<T> withSuccess(String message, List<String> params) {
      statusMessage = StatusMessage.of(ErrorCode.SUCCESS.getErrorCode(), message, params);
      return this;
    }

    public Builder<T> hasError(int errorCode, String message) {
      return hasError(errorCode, message, Collections.emptyList());
    }

    public Builder<T> hasError(int errorCode, String message, List<String> params) {
      statusMessage = StatusMessage.of(errorCode, message, params);
      return this;
    }

    public Builder<T> withResults(T results) {
      this.results = results;
      return this;
    }

    public ResultDto<T> build() {
      return ResultDto.of(statusMessage, results);
    }
  }

}
