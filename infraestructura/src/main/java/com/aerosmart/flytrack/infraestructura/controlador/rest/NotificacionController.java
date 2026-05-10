package com.aerosmart.flytrack.infraestructura.controlador.rest;

import com.aerosmart.flytrack.aplicacion.dto.NotificacionDTO;
import com.aerosmart.flytrack.aplicacion.servicio.NotificacionServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
private final NotificacionServicio servicio;

public NotificacionController(NotificacionServicio servicio) {
    this.servicio = servicio;
}

@GetMapping
public List<NotificacionDTO> listar(HttpServletRequest request) {
    String email = (String) request.getAttribute("email");
    if (email == null) {
    throw new IllegalArgumentException("No autenticado");
    }
    var passenger = servicio.buscarPasajeroPorEmail(email);
    return servicio.obtenerNotificacionesDTO(passenger.getId());
}
}