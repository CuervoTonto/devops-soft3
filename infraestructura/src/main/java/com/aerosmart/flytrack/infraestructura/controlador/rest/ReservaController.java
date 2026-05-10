package com.aerosmart.flytrack.infraestructura.controlador.rest;

import com.aerosmart.flytrack.aplicacion.dto.ReservaDTO;
import com.aerosmart.flytrack.aplicacion.servicio.ReservaServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
private final ReservaServicio servicio;

public ReservaController(ReservaServicio servicio) {
    this.servicio = servicio;
}

@PostMapping
public ReservaDTO crearReserva(HttpServletRequest request, @RequestBody ReservaRequest req) {
    String email = (String) request.getAttribute("email");
    if (email == null) {
    throw new IllegalStateException("Debes iniciar sesión para reservar");
    }
    return servicio.crearReservaDTO(email, UUID.fromString(req.flightScheduleId()));
}

public record ReservaRequest(String flightScheduleId) {}
}