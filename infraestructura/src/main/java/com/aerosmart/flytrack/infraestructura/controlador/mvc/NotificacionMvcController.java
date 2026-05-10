package com.aerosmart.flytrack.infraestructura.controlador.mvc;

import com.aerosmart.flytrack.aplicacion.dto.NotificacionDTO;
import com.aerosmart.flytrack.aplicacion.servicio.NotificacionServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionMvcController {
private final NotificacionServicio servicio;

public NotificacionMvcController(NotificacionServicio servicio) {
    this.servicio = servicio;
}

@GetMapping
public String verNotificaciones(HttpServletRequest request, Model model) {
    String email = (String) request.getAttribute("email");
    if (email != null) {
    var passenger = servicio.buscarPasajeroPorEmail(email);
    List<NotificacionDTO> notificaciones = servicio.obtenerNotificacionesDTO(passenger.getId());
    model.addAttribute("notificaciones", notificaciones);
    model.addAttribute("email", email);
    }
    return "notificaciones";
}
}