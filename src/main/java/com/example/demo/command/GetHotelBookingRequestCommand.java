package com.example.demo.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetHotelBookingRequestCommand {
  private String bookingId;
}
