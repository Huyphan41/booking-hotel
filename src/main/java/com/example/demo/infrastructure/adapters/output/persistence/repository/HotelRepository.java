package com.example.demo.infrastructure.adapters.output.persistence.repository;

import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelEntity;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends BaseJpaRepository<HotelEntity, UUID>,
    ListPagingAndSortingRepository<HotelEntity, UUID>, JpaSpecificationExecutor<HotelEntity> {

  List<HotelEntity> findByDestinationContainingIgnoreCase(String destination);
}
