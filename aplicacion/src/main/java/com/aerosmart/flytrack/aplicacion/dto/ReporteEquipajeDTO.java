package com.aerosmart.flytrack.aplicacion.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReporteEquipajeDTO(
    UUID id,
    UUID bookingId,
    String tipoIncidencia,
    String descripcion,
    String estado,
    LocalDateTime reportadoEn
) {}