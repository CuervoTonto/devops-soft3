package com.aerosmart.flytrack.dominio.valueobject;

public record Email(String value) {
public Email {
    if (value == null || !value.contains("@")) {
    throw new IllegalArgumentException("Email invalido: " + value);
    }
}
}