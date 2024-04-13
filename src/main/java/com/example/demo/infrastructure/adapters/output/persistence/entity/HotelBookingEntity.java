package com.example.demo.infrastructure.adapters.output.persistence.entity;

import com.example.demo.domain.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "hotel_booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelBookingEntity {

  @Id
  @UuidGenerator
  private UUID id;
  private UUID userId;
  private UUID hotelId;
  private LocalDate startDate;
  private LocalDate endDate;
  private BookingStatus status;
}
