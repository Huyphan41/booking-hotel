package com.example.demo.usecase;

import com.example.demo.application.ports.input.usecasehandler.CreateHotelBookingUseCaseHandler;
import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.command.CreateHotelBookingRequestCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CreateHotelBookingUseCaseHandlerTest {

  @InjectMocks
  private CreateHotelBookingUseCaseHandler createHotelBookingUseCaseHandler;

  @Mock
  private HotelBookingPort hotelBookingPort;

  @Test
  public void test_create_booking_should_be_ok() {
    Mockito.when(hotelBookingPort.create(Mockito.any())).thenReturn(UUID.randomUUID().toString());

    var stringResultDto = createHotelBookingUseCaseHandler.handle(initHotelBookingRequestCommand());

    assertThat(stringResultDto.getResults()).isNotEmpty();
  }

  @Test
  public void test_create_booking_without_exist_hotelId_should_be_fail() {
    Mockito.when(hotelBookingPort.create(Mockito.any())).thenThrow(new RuntimeException("Can't find hotel in DB"));

    var stringResultDto = createHotelBookingUseCaseHandler.handle(initHotelBookingRequestCommand());

    assertThat(stringResultDto.getStatusMessage().isSuccess()).isFalse();
    assertThat(stringResultDto.getStatusMessage().getMessage()).isEqualTo("Can't find hotel in DB");
  }

  private CreateHotelBookingRequestCommand initHotelBookingRequestCommand() {
    return CreateHotelBookingRequestCommand.builder()
            .userId(UUID.randomUUID().toString())
            .hotelId(UUID.randomUUID().toString())
            .startDate(LocalDate.now())
            .endDate(LocalDate.now().plusDays(2))
            .build();
  }
}
