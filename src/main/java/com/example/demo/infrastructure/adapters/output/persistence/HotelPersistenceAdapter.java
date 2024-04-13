package com.example.demo.infrastructure.adapters.output.persistence;

import com.example.demo.application.dto.PageObj;
import com.example.demo.application.ports.output.HotelPort;
import com.example.demo.command.ListHotelRequestCommand;
import com.example.demo.domain.HotelDomain;
import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import com.example.demo.infrastructure.adapters.output.persistence.mapper.DataMapper;
import com.example.demo.infrastructure.adapters.output.persistence.repository.HotelRepository;
import com.example.demo.infrastructure.adapters.output.persistence.specification.HotelFilterSpec;
import com.example.demo.infrastructure.adapters.output.persistence.specification.HotelSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class HotelPersistenceAdapter implements HotelPort {

  private final HotelRepository hotelRepository;

  @Override
  public PageObj<HotelDomain> getListPagination(ListHotelRequestCommand command) {
    Pageable pageable = PageRequest.of(command.getPage() > 0 ? command.getPage() - 1 : 0, command.getSize());
    HotelFilterSpec hotelFilterSpec = DataMapper.mapToHotelFilterSpec(command);
    Specification<HotelEntity> specification = HotelSpecification.hotelSearchSpec(hotelFilterSpec);
    Page<HotelEntity> hotelEntityPage = hotelRepository.findAll(specification, pageable);
    List<HotelDomain> hotelDomainList = hotelEntityPage.getContent()
        .stream()
        .map(DataMapper::mapToHotelDomain)
        .collect(Collectors.toList());

    PageObj<HotelDomain> hotelDomainPage = new PageObj<>();
    hotelDomainPage.setItems(hotelDomainList);
    hotelDomainPage.setTotalItem(hotelEntityPage.getTotalElements());
    hotelDomainPage.setTotalPages(hotelEntityPage.getTotalPages());
    hotelDomainPage.setPageSize(command.getSize());
    hotelDomainPage.setPageIndex(command.getPage());

    return hotelDomainPage;
  }

  @Override
  public Optional<HotelDomain> getHotelById(UUID id) {
    return hotelRepository.findById(id).map(DataMapper::mapToHotelDomain);
  }
}
