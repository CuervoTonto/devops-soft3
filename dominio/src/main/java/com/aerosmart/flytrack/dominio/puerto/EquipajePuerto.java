package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import java.util.UUID;

public interface EquipajePuerto {
  BaggageReport reportarIncidencia(UUID bookingId, String tipoIncidencia, String descripcion);
}