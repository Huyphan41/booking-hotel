package com.example.demo.common;

import com.example.demo.application.dto.ResultDto;

public interface UseCase<TCommand, TResult> {
  ResultDto<TResult> handle(TCommand command);
}
