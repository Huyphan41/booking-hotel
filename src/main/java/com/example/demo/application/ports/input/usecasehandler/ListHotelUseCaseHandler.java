package com.example.demo.application.ports.input.usecasehandler;

import com.example.demo.application.dto.HotelDto;
import com.example.demo.application.dto.PageObj;
import com.example.demo.application.dto.ResultDto;
import com.example.demo.application.mapper.DataMapperDto;
import com.example.demo.application.ports.input.ListHotelUseCase;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.domain.HotelDomain;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListHotelUseCaseHandler implements ListHotelUseCase {

  private static final Logger logger = LoggerFactory.getLogger(ListHotelUseCaseHandler.class);

  private final HotelPort hotelPort;

  @Override
  public ResultDto<PageObj<HotelDto>> handle(ListHotelRequestCommand command) {
    logger.info("Received Command:: {} ", command);
    PageObj<HotelDomain> hotelDomainPage = hotelPort.getListPagination(command);
    List<HotelDto> hotelDtoList = hotelDomainPage.getItems()
        .stream()
        .map(DataMapperDto::mapToHotelDto)
        .collect(Collectors.toList());

    PageObj<HotelDto> results = PageObj.<HotelDto>builder()
        .items(hotelDtoList)
        .pageIndex(hotelDomainPage.getPageIndex())
        .pageSize(hotelDomainPage.getPageSize())
        .totalItem(hotelDomainPage.getTotalItem())
        .totalPages(hotelDomainPage.getTotalPages())
        .build();
    return ResultDto.success(results);
  }
}
