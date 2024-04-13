package com.example.demo.infrastructure.adapters.output.persistence;

import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.domain.BookingDomain;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelBookingEntity;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import com.example.demo.infrastructure.adapters.output.persistence.mapper.DataMapper;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelBookingRepository;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class BookingPersistenceAdapter implements HotelBookingPort {

  private final HotelBookingRepository hotelBookingRepository;
  private final HotelRepository hotelRepository;

  @Override
  public String create(BookingDomain bookingDomain) {
    if (StringUtils.hasLength(bookingDomain.getHotelId().toString()) &&
        getHotelEntityById(bookingDomain.getHotelId()).isEmpty()) {
      throw new RuntimeException("Can't find hotel in DB");
    }
    var hotelBookingEntity = DataMapper.mapToHotelBookingEntity(bookingDomain);
    var bookingEntityPersisted = hotelBookingRepository.persist(hotelBookingEntity);
    return bookingEntityPersisted.getId().toString();
  }

  @Override
  public Optional<BookingDomain> getBookingById(String id) {
    Optional<HotelBookingEntity> bookingEntityOpt = hotelBookingRepository.findById(UUID.fromString(id));
    if (bookingEntityOpt.isEmpty()) {
      throw new RuntimeException("Can't find hotel booking in DB");
    }
    return Optional.of(DataMapper.mapToBookingDomain(bookingEntityOpt.get()));
  }

  private Optional<HotelEntity> getHotelEntityById(UUID hotelId) {
    return hotelRepository.findById(hotelId);
  }
}
