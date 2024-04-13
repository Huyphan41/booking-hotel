package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.BookingStatus;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelBookingEntity;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelBookingRepository;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final HotelRepository hotelRepository;
  private final HotelBookingRepository hotelBookingRepository;

  @Override
  public void run(String... args) throws Exception {
    // Checking if data already exists to avoid duplicates
    if (hotelRepository.count() == 0) {
      HotelEntity hotel1 = new HotelEntity(null, "Hotel California", "Los Angeles", "US");
      HotelEntity hotel2 = new HotelEntity(null, "Grand Plaza", "New York", "US");
      hotelRepository.persist(hotel1);
      hotelRepository.persist(hotel2);

      HotelBookingEntity hotelBookingEntity = new HotelBookingEntity(
          null, UUID.randomUUID(), hotel1.getId(), LocalDate.now(),
          LocalDate.now().plusDays(1), BookingStatus.CONFIRMED);
      hotelBookingRepository.persist(hotelBookingEntity);
    }
  }
}
