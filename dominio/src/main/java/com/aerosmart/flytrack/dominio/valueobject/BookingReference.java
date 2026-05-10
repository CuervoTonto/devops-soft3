package com.aerosmart.flytrack.dominio.valueobject;

public record BookingReference(String value) {
private static final int LENGTH = 6;

public BookingReference {
    if (value == null || value.length() != LENGTH) {
    throw new IllegalArgumentException("Referencia de reserva debe tener 6 caracteres");
    }
}
}