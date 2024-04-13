package com.example.demo.application.mapper;

import com.example.demo.command.CreateHotelBookingRequestCommand;
import com.example.demo.domain.BookingDomain;
import com.example.demo.domain.BookingStatus;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class DomainMapper {

  public static BookingDomain mapToHotelBookingDomain(CreateHotelBookingRequestCommand command) {
    return BookingDomain.builder()
        .id(UUID.randomUUID())
        .userId(UUID.fromString(command.getUserId()))
        .hotelId(UUID.fromString(command.getHotelId()))
        .startDate(command.getStartDate())
        .endDate(command.getEndDate())
        .status(BookingStatus.PENDING)
        .build();
  }
}