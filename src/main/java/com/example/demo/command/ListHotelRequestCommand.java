package com.example.demo.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListHotelRequestCommand {
  private Integer page;
  private Integer size;
  private String sort;
  private String destination;
}
