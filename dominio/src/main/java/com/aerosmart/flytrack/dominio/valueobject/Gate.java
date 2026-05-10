package com.aerosmart.flytrack.dominio.valueobject;

public record Gate(String value) {
  public Gate {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("La puerta no puede estar vacia");
    }
  }
}