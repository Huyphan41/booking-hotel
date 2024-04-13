package com.example.demo.infrastructure.adapters.output.persistence.specification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HotelFilterSpec {
  private String destination;
}
