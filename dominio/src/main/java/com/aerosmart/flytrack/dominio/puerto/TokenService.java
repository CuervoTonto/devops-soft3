package com.aerosmart.flytrack.dominio.puerto;

public interface TokenService {
  String generarToken(String email);
  String obtenerEmailDelToken(String token);
  boolean validarToken(String token);
}