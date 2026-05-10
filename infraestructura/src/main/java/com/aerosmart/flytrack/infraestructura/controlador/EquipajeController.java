package com.aerosmart.flytrack.infraestructura.controlador;

import com.aerosmart.flytrack.aplicacion.dto.ReporteEquipajeDTO;
import com.aerosmart.flytrack.aplicacion.servicio.EquipajeServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipaje")
public class EquipajeController {
  private final EquipajeServicio servicio;

  public EquipajeController(EquipajeServicio servicio) {
    this.servicio = servicio;
  }

  @PostMapping("/reportar")
  public ReporteEquipajeDTO reportar(@RequestBody ReporteEquipajeRequest request) {
    return servicio.reportarIncidenciaDTO(
        request.bookingId(),
        request.tipoIncidencia(),
        request.descripcion()
    );
  }

  @GetMapping("/mis-reportes")
  public List<ReporteEquipajeDTO> misReportes(HttpServletRequest request) {
    String email = (String) request.getAttribute("email");
    if (email == null) {
      throw new IllegalStateException("Debes iniciar sesión");
    }
    return servicio.listarPorEmailDTO(email);
  }

  @GetMapping("/booking/{bookingId}")
  public List<ReporteEquipajeDTO> reportesPorBooking(@PathVariable String bookingId) {
    return servicio.listarPorBookingDTO(UUID.fromString(bookingId));
  }

  public record ReporteEquipajeRequest(String bookingId, String tipoIncidencia, String descripcion) {}
}