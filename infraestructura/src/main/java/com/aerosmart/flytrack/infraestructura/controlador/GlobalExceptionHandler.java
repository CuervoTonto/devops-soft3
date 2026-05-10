package com.aerosmart.flytrack.infraestructura.controlador;

import com.aerosmart.flytrack.dominio.excepcion.ReservaNoEncontradaException;
import com.aerosmart.flytrack.dominio.excepcion.VueloNoDisponibleException;
import com.aerosmart.flytrack.dominio.excepcion.PeriodoReporteExpiradoException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ReservaNoEncontradaException.class)
public ResponseEntity<Map<String, String>> handleReservaNoEncontrada(ReservaNoEncontradaException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("message", ex.getMessage()));
}

@ExceptionHandler(VueloNoDisponibleException.class)
public ResponseEntity<Map<String, String>> handleVueloNoDisponible(VueloNoDisponibleException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of("message", ex.getMessage()));
}

@ExceptionHandler(PeriodoReporteExpiradoException.class)
public ResponseEntity<Map<String, String>> handlePeriodoExpirado(PeriodoReporteExpiradoException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of("message", ex.getMessage()));
}
}