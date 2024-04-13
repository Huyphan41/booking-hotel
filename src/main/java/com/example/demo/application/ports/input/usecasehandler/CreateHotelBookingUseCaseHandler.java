package com.example.demo.application.ports.input.usecasehandler;

import com.example.demo.application.dto.ErrorCode;
import com.example.demo.application.dto.ResultDto;
import com.example.demo.application.mapper.DomainMapper;
import com.example.demo.application.ports.input.CreateHotelBookingUseCase;
import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.command.CreateHotelBookingRequestCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class CreateHotelBookingUseCaseHandler implements CreateHotelBookingUseCase {

  private static final Logger logger = LoggerFactory.getLogger(CreateHotelBookingUseCaseHandler.class);
  private final HotelBookingPort hotelBookingPort;

  @Override
  public ResultDto<String> handle(CreateHotelBookingRequestCommand createHotelBookingRequestCommand) {
    try {
      logger.info("Received Command:: {} ", createHotelBookingRequestCommand);
      var bookingDomain = DomainMapper.mapToHotelBookingDomain(createHotelBookingRequestCommand);
      hotelBookingPort.create(bookingDomain);
      // Optionally, send the event to an analytics service
      // AnalyticsService.recordEvent("BookingCreated", request);

      return ResultDto
          .<String>newBuilder()
          .withSuccess(ErrorCode.SUCCESS.getMessageDisplayKey())
          .withResults(bookingDomain.getId().toString())
          .build();
    } catch (Exception e) {
      return ResultDto
          .<String>newBuilder()
          .hasError(ErrorCode.INTERNAL_ERROR.getErrorCode(), e.getMessage())
          .withResults("")
          .build();
    }
  }
}
