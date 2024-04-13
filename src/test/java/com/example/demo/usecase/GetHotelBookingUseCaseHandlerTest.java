package com.example.demo.usecase;

import com.example.demo.application.ports.input.usecasehandler.GetHotelBookingUseCaseHandler;
import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.command.GetHotelBookingRequestCommand;
import com.example.demo.domain.BookingDomain;
import com.example.demo.domain.BookingStatus;
import com.example.demo.domain.HotelDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GetHotelBookingUseCaseHandlerTest {
  private static final String HOTEL_ID = UUID.randomUUID().toString();

  @Mock
  private HotelPort hotelPort;

  @Mock
  private HotelBookingPort hotelBookingPort;

  @InjectMocks
  private GetHotelBookingUseCaseHandler getHotelBookingDetailUseCase;

  @Test
  public void test_get_hotel_booking_should_be_ok() {
    var bookingDomainOptional = initBookingDomain();
    var hotelDomainOptional = initHotelDomain();
    Mockito.when(hotelBookingPort.getBookingById(Mockito.anyString())).thenReturn(bookingDomainOptional);
    Mockito.when(hotelPort.getHotelById(Mockito.any())).thenReturn(hotelDomainOptional);

    var hotelBookingDtoResultDto = getHotelBookingDetailUseCase.handle(
        GetHotelBookingRequestCommand.builder().bookingId(UUID.randomUUID().toString()).build());
    var hotelBookingDto = hotelBookingDtoResultDto.getResults();

    assertThat(hotelBookingDto).isNotNull();
    assertThat(hotelBookingDto.getHotelName()).isEqualTo("New World");
    assertThat(hotelBookingDto.getHotelId()).isEqualTo(HOTEL_ID);
    assertThat(hotelBookingDto.getStatus()).isEqualTo("CONFIRMED");
  }

  @Test
  public void test_get_hotel_booking_not_exist_should_be_fail() {
    Mockito.when(hotelBookingPort.getBookingById(Mockito.anyString()))
        .thenThrow(new RuntimeException("Can't find hotel booking in DB"));

    var hotelBookingDtoResultDto = getHotelBookingDetailUseCase.handle(
        GetHotelBookingRequestCommand.builder().bookingId(UUID.randomUUID().toString()).build());

    assertThat(hotelBookingDtoResultDto.getStatusMessage().isSuccess()).isFalse();
    assertThat(hotelBookingDtoResultDto.getStatusMessage().getMessage()).isEqualTo("Can't find hotel booking in DB");
  }

  private Optional<HotelDomain> initHotelDomain() {
    return Optional.of(
        HotelDomain.builder().id(UUID.fromString(HOTEL_ID)).name("New World").destination("HCM").address("Q1").build());
  }

  private Optional<BookingDomain> initBookingDomain() {
    return Optional.of(BookingDomain.builder()
        .id(UUID.randomUUID())
        .userId(UUID.randomUUID())
        .hotelId(UUID.fromString(HOTEL_ID))
        .startDate(LocalDate.now())
        .endDate(LocalDate.now().plusDays(1))
        .status(BookingStatus.CONFIRMED)
        .build());
  }
}
