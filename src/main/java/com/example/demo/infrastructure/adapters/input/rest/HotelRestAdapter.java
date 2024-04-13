package com.example.demo.infrastructure.adapters.input.rest;

import com.example.demo.application.dto.HotelBookingDto;
import com.example.demo.application.dto.HotelDto;
import com.example.demo.application.dto.PageObj;
import com.example.demo.application.dto.ResultDto;
import com.example.demo.application.ports.input.CreateHotelBookingUseCase;
import com.example.demo.application.ports.input.GetHotelBookingDetailUseCase;
import com.example.demo.application.ports.input.ListHotelUseCase;
import com.example.demo.command.GetHotelBookingRequestCommand;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.infrastructure.adapters.output.persistence.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HotelRestAdapter {
  private final CreateHotelBookingUseCase createHotelBookingUseCase;
  private final GetHotelBookingDetailUseCase getHotelBookingDetailUseCase;
  private final ListHotelUseCase listHotelUseCase;

  @PostMapping("/hotels/bookings")
  public ResponseEntity<ResultDto<String>> createBooking(@RequestBody CreateBookingRequest bookingRequest) {
    log.info("Create booking:: request:: {}", bookingRequest);
    var createHotelBookingRequestCommand =
        DataMapper.mapToCreateHotelBookingRequestCommand(bookingRequest);
    var resultDto = createHotelBookingUseCase.handle(createHotelBookingRequestCommand);
    return ResponseEntity.ok(resultDto);
  }

  @GetMapping("/hotels")
  public ResponseEntity<ResultDto<PageObj<HotelDto>>> getHotelsByDestination(@RequestParam String destination) {
    log.info("Get hotel by destination:: request:: {}", destination);
    var command =
        ListHotelRequestCommand.builder().page(1).size(10).destination(destination).build();
    return ResponseEntity.ok(listHotelUseCase.handle(command));
  }

  @GetMapping("/hotels/bookings/{id}")
  public ResponseEntity<ResultDto<HotelBookingDto>> getBookingById(@PathVariable String id) {
    log.info("Get booking by ID:: request:: {}", id);
    var command = GetHotelBookingRequestCommand.builder().bookingId(id).build();
    return ResponseEntity.ok(getHotelBookingDetailUseCase.handle(command));
  }
}
