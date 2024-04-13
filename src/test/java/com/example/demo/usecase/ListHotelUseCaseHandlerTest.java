package com.example.demo.usecase;

import com.example.demo.application.dto.PageObj;
import com.example.demo.application.ports.input.usecasehandler.ListHotelUseCaseHandler;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.domain.HotelDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ListHotelUseCaseHandlerTest {

  @Mock
  private HotelPort hotelPort;

  @InjectMocks
  private ListHotelUseCaseHandler listHotelUseCaseHandler;

  @Test
  public void test_list_hotel_by_destination_should_be_ok() {
    var hotelDomain =
        HotelDomain.builder().id(UUID.randomUUID()).name("New World").destination("HCM").address("Q1").build();
    PageObj<HotelDomain> hotelDomainPage = new PageObj<>();
    hotelDomainPage.setItems(Collections.singletonList(hotelDomain));
    hotelDomainPage.setTotalItem(1);
    hotelDomainPage.setTotalPages(1);
    hotelDomainPage.setPageSize(10);
    hotelDomainPage.setPageIndex(1);
    Mockito.when(hotelPort.getListPagination(Mockito.any())).thenReturn(hotelDomainPage);

    var listHotelRequestCommand =
        ListHotelRequestCommand.builder().page(1).size(10).destination("New York").build();
    var pageObjResultDto = listHotelUseCaseHandler.handle(listHotelRequestCommand);

    assertThat(pageObjResultDto.getStatusMessage().isSuccess()).isTrue();
    assertThat(pageObjResultDto.getResults().getItems().size()).isEqualTo(1);
  }
}
