package com.example.demo.infrastructure.adapters.output.persistence.mapper;

import com.example.demo.command.CreateHotelBookingRequestCommand;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.domain.BookingDomain;
import com.example.demo.domain.HotelDomain;
import com.example.demo.infrastructure.adapters.input.rest.CreateBookingRequest;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelBookingEntity;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import com.example.demo.infrastructure.adapters.output.persistence.specification.HotelFilterSpec;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataMapper {

  public static HotelBookingEntity mapToHotelBookingEntity(BookingDomain bookingDomain) {
    return HotelBookingEntity.builder()
        .hotelId(bookingDomain.getHotelId())
        .userId(bookingDomain.getUserId())
        .startDate(bookingDomain.getStartDate())
        .endDate(bookingDomain.getEndDate())
        .status(bookingDomain.getStatus())
        .build();
  }

  public static BookingDomain mapToBookingDomain(HotelBookingEntity entity) {
    return BookingDomain.builder()
        .id(entity.getId())
        .hotelId(entity.getHotelId())
        .userId(entity.getUserId())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .status(entity.getStatus())
        .build();
  }

  public static HotelDomain mapToHotelDomain(HotelEntity entity) {
    return HotelDomain.builder()
        .id(entity.getId())
        .name(entity.getName())
        .address(entity.getAddress())
        .destination(entity.getDestination())
        .build();
  }

  public static HotelFilterSpec mapToHotelFilterSpec(ListHotelRequestCommand command) {
    return HotelFilterSpec.builder().destination(command.getDestination()).build();
  }

  public static CreateHotelBookingRequestCommand mapToCreateHotelBookingRequestCommand(
      CreateBookingRequest bookingRequest) {
    return CreateHotelBookingRequestCommand.builder()
        .userId(bookingRequest.getUserId())
        .hotelId(bookingRequest.getHotelId())
        .startDate(bookingRequest.getStartDate())
        .endDate(bookingRequest.getEndDate())
        .build();
  }
}
