package com.example.demo.application.ports.input;

import com.example.demo.command.CreateHotelBookingRequestCommand;
import com.example.demo.common.UseCase;

public interface CreateHotelBookingUseCase extends UseCase<CreateHotelBookingRequestCommand, String> {
}
