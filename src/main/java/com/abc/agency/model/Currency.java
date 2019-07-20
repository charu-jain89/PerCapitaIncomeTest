package com.abc.agency.model;

public enum Currency {
  INR(66d),
  GBP(0.67d),
  HKD(8d),
  SGP(1.5d),
  USD(1d);

  private Double rate;

  public Double getRate() {
    return rate;
  }

  Currency(Double rate) {
    this.rate = rate;
  }
}
