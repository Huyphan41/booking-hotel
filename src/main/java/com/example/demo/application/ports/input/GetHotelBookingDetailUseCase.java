package com.example.demo.application.ports.input;

import com.example.demo.application.dto.HotelBookingDto;
import com.example.demo.command.GetHotelBookingRequestCommand;
import com.example.demo.common.UseCase;

public interface GetHotelBookingDetailUseCase extends UseCase<GetHotelBookingRequestCommand, HotelBookingDto> {
}
