package com.aerosmart.flytrack.dominio.valueobject;

import java.util.regex.Pattern;

public record FlightNumber(String value) {
  private static final Pattern PATTERN = Pattern.compile("^[A-Z]{2}\\d{1,4}$");

  public FlightNumber {
    if (value == null || !PATTERN.matcher(value).matches()) {
      throw new IllegalArgumentException("Formato de numero de vuelo invalido: " + value);
    }
  }
}