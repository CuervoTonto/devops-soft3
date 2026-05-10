package com.aerosmart.flytrack.infraestructura.controlador;

import com.aerosmart.flytrack.aplicacion.dto.ReporteEquipajeDTO;
import com.aerosmart.flytrack.aplicacion.servicio.EquipajeServicio;
import java.util.UUID;
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
        UUID.fromString(request.bookingId()),
        request.tipoIncidencia(),
        request.descripcion()
    );
  }

  public record ReporteEquipajeRequest(String bookingId, String tipoIncidencia, String descripcion) {}
}