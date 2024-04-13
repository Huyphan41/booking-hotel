package com.example.demo.command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateHotelBookingRequestCommand {
  private String userId;
  private String hotelId;
  private LocalDate startDate;
  private LocalDate endDate;
}
