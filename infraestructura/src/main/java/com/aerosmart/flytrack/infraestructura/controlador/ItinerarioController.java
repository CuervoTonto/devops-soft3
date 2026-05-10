package com.aerosmart.flytrack.infraestructura.controlador;

import com.aerosmart.flytrack.aplicacion.dto.ItinerarioDTO;
import com.aerosmart.flytrack.aplicacion.servicio.ItinerarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vuelos")
public class ItinerarioController {
  private final ItinerarioServicio servicio;

  public ItinerarioController(ItinerarioServicio servicio) {
    this.servicio = servicio;
  }

  @GetMapping("/itinerario")
  public List<ItinerarioDTO> consultarPorEmail(HttpServletRequest request) {
    String email = (String) request.getAttribute("email");
    if (email == null) {
      throw new IllegalArgumentException("No autenticado");
    }
    return servicio.consultarPorEmailDTO(email);
  }

  @GetMapping("/itinerario/{referencia}")
  public ItinerarioDTO consultarPorReferencia(@PathVariable String referencia) {
    return servicio.consultarPorReferenciaDTO(referencia)
        .orElseThrow(() -> new RuntimeException("Itinerario no encontrado"));
  }
}