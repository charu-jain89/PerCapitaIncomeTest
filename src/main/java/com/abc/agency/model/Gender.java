package com.abc.agency.model;

public enum Gender {
  M("Male"),
  F("Female");

  private String value;

  public String getValue() {
    return value;
  }

  Gender(String gender) {
    this.value = gender;
  }
}
