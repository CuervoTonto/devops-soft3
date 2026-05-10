package com.aerosmart.flytrack.dominio.valueobject;

public record IATACode(String value) {
public IATACode {
    if (value == null || value.length() != 3) {
    throw new IllegalArgumentException("Codigo IATA debe tener 3 caracteres");
    }
}
}