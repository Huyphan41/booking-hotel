package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelDomain {
  private UUID id;
  private String name;
  private String destination; // City or location of the hotel
  private String address;
}
