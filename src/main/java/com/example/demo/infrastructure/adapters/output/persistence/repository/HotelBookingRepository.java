package com.example.demo.infrastructure.adapters.output.persistence.repository;

import com.example.demo.infrastructure.adapters.output.persistence.entity.HotelBookingEntity;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelBookingRepository extends BaseJpaRepository<HotelBookingEntity, UUID>,
    ListPagingAndSortingRepository<HotelBookingEntity, UUID> {
}
