package com.example.demo.application.ports.output;

import com.example.demo.application.dto.PageObj;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.domain.HotelDomain;

import java.util.Optional;
import java.util.UUID;

public interface HotelPort {
  PageObj<HotelDomain> getListPagination(ListHotelRequestCommand command);
  Optional<HotelDomain> getHotelById(UUID id);
}
