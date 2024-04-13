package com.example.demo.application.ports.output;

import com.example.demo.domain.BookingDomain;

import java.util.Optional;

public interface HotelBookingPort {
  String create(BookingDomain bookingDomain);
  Optional<BookingDomain> getBookingById(String id);
}
