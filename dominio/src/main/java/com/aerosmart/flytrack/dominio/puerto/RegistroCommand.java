package com.aerosmart.flytrack.dominio.puerto;

public record RegistroCommand(
    String email,
    String password,
    String firstName,
    String lastName,
    String passportNumber
) {
  public void validar() {
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("El email es requerido");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("La contrasena es requerida");
    }
    if (firstName == null || firstName.isBlank()) {
      throw new IllegalArgumentException("El nombre es requerido");
    }
    if (lastName == null || lastName.isBlank()) {
      throw new IllegalArgumentException("El apellido es requerido");
    }
    if (passportNumber == null || passportNumber.isBlank()) {
      throw new IllegalArgumentException("El numero de pasaporte es requerido");
    }
  }
}