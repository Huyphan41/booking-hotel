package com.example.demo.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HotelBookingDto {
  private String id;
  private String userId;
  private String userName;
  private String hotelId;
  private String hotelName;
  private LocalDate startDate;
  private LocalDate endDate;
  private String status;
}
