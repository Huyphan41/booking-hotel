package com.example.demo.application.ports.input;

import com.example.demo.application.dto.HotelDto;
import com.example.demo.application.dto.PageObj;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.common.UseCase;

public interface ListHotelUseCase extends UseCase<ListHotelRequestCommand, PageObj<HotelDto>> {
}
