package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageObj<T> {
  private long totalItem;
  private List<T> items;
  private int pageSize;
  private int pageIndex;
  private int totalPages;
}
