package com.aerosmart.flytrack.dominio.puerto;

public interface PasswordEncoderPuerto {
  String encode(String rawPassword);
  boolean matches(String rawPassword, String encodedPassword);
}