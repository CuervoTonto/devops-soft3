package com.aerosmart.flytrack.dominio.excepcion;

public class ReservaNoEncontradaException extends RuntimeException {
  public ReservaNoEncontradaException(String message) {
    super(message);
  }
}