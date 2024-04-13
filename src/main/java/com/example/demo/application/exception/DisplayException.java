package com.example.demo.application.exception;

import com.example.demo.application.dto.StatusMessage;

public class DisplayException extends RuntimeException {
  private final StatusMessage statusMessage;
  private final String[] params;

  public DisplayException(StatusMessage statusMessage) {
    this(statusMessage, new String[0]);
  }

  public DisplayException(StatusMessage statusMessage, String... params) {
    this.statusMessage = statusMessage;
    this.params = params;
  }

  public StatusMessage getStatusMessage() {
    return statusMessage;
  }

  public String[] getParams() {
    return params;
  }
}
