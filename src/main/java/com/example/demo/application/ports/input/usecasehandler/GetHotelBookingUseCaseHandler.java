package com.example.demo.application.ports.input.usecasehandler;

import com.example.demo.application.dto.ErrorCode;
import com.example.demo.application.dto.HotelBookingDto;
import com.example.demo.application.dto.ResultDto;
import com.example.demo.application.mapper.DataMapperDto;
import com.example.demo.application.ports.input.GetHotelBookingDetailUseCase;
import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.command.GetHotelBookingRequestCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class GetHotelBookingUseCaseHandler implements GetHotelBookingDetailUseCase {

  private static final Logger logger = LoggerFactory.getLogger(GetHotelBookingUseCaseHandler.class);

  private final HotelPort hotelPort;
  private final HotelBookingPort hotelBookingPort;

  @Override
  public ResultDto<HotelBookingDto> handle(GetHotelBookingRequestCommand command) {
    logger.info("Received Command:: {} ", command);
    var bookingDto = HotelBookingDto.builder().build();

    try {
      var bookingDomainOptional = hotelBookingPort.getBookingById(command.getBookingId());
      if (bookingDomainOptional.isPresent()) {
        var bookingDomain = bookingDomainOptional.get();
        var hotelDomain = hotelPort.getHotelById(bookingDomain.getHotelId());
        bookingDto = DataMapperDto.mapToHotelBookingDto(hotelDomain.get(), bookingDomain);
      }

      return ResultDto
          .<HotelBookingDto>newBuilder()
          .withSuccess(ErrorCode.SUCCESS.getMessageDisplayKey())
          .withResults(bookingDto)
          .build();
    } catch (Exception e) {
      return ResultDto
          .<HotelBookingDto>newBuilder()
          .hasError(ErrorCode.INTERNAL_ERROR.getErrorCode(), e.getMessage())
          .withResults(bookingDto)
          .build();
    }
  }
}
