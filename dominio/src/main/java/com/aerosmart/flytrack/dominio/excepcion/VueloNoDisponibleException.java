package com.aerosmart.flytrack.dominio.excepcion;

public class VueloNoDisponibleException extends RuntimeException {
  public VueloNoDisponibleException(String message) {
    super(message);
  }
}