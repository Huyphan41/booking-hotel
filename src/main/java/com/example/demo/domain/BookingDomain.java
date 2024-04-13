package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingDomain {
  private UUID id;
  private UUID userId;
  private UUID hotelId;
  private LocalDate startDate;
  private LocalDate endDate;
  private BookingStatus status;
}
