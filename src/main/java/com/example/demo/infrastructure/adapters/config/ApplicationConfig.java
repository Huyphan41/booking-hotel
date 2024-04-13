package com.example.demo.infrastructure.adapters.config;

import com.example.demo.application.ports.input.CreateHotelBookingUseCase;
import com.example.demo.application.ports.input.GetHotelBookingDetailUseCase;
import com.example.demo.application.ports.input.ListHotelUseCase;
import com.example.demo.application.ports.input.usecasehandler.CreateHotelBookingUseCaseHandler;
import com.example.demo.application.ports.input.usecasehandler.GetHotelBookingUseCaseHandler;
import com.example.demo.application.ports.input.usecasehandler.ListHotelUseCaseHandler;
import com.example.demo.application.ports.output.HotelBookingPort;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.infrastructure.adapters.output.persistence.BookingPersistenceAdapter;
import com.example.demo.infrastructure.adapters.output.persistence.HotelPersistenceAdapter;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelBookingRepository;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public CreateHotelBookingUseCase createHotelBookingUseCase(HotelBookingPort hotelBookingPort) {
    return new CreateHotelBookingUseCaseHandler(hotelBookingPort);
  }

  @Bean
  public GetHotelBookingDetailUseCase getHotelBookingDetailUseCase(
      HotelPort hotelPort, HotelBookingPort hotelBookingPort) {
    return new GetHotelBookingUseCaseHandler(hotelPort, hotelBookingPort);
  }

  @Bean
  public ListHotelUseCase listHotelUseCase(HotelPort hotelPort) {
    return new ListHotelUseCaseHandler(hotelPort);
  }

  @Bean
  public HotelPort hotelPort(HotelRepository hotelRepository) {
    return new HotelPersistenceAdapter(hotelRepository);
  }

  @Bean
  public HotelBookingPort hotelBookingPort(
      HotelBookingRepository hotelBookingRepository, HotelRepository hotelRepository) {
    return new BookingPersistenceAdapter(hotelBookingRepository, hotelRepository);
  }
}
