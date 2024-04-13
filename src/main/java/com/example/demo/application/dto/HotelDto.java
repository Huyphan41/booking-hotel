package com.example.demo.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDto {
  private String id;
  private String name;
  private String destination; // City or location of the hotel
  private String address;
}
