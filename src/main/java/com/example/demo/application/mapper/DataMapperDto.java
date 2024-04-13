package com.example.demo.application.mapper;

import com.example.demo.application.dto.HotelBookingDto;
import com.example.demo.application.dto.HotelDto;
import com.example.demo.domain.BookingDomain;
import com.example.demo.domain.HotelDomain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataMapperDto {

  public static HotelBookingDto mapToHotelBookingDto(HotelDomain hotelDomain, BookingDomain bookingDomain) {
    return HotelBookingDto.builder()
        .id(bookingDomain.getId().toString())
        .userId(bookingDomain.getUserId().toString())
        .hotelId(hotelDomain.getId().toString())
        .hotelName(hotelDomain.getName())
        .status(bookingDomain.getStatus().name())
        .startDate(bookingDomain.getStartDate())
        .endDate(bookingDomain.getEndDate())
        .build();
  }

  public static HotelDto mapToHotelDto(HotelDomain hotelDomain) {
    return HotelDto.builder()
        .id(hotelDomain.getId().toString())
        .name(hotelDomain.getName())
        .address(hotelDomain.getAddress())
        .destination(hotelDomain.getDestination())
        .build();
  }
}
