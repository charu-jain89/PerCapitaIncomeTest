package com.abc.agency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Income {

  private String city;
  private String country;
  private Gender gender;
  private Currency currency;
  private Double avgIncome;
}
