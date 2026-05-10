package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;

public interface EquipajePuerto {
BaggageReport reportarIncidencia(String referenciaReserva, String tipoIncidencia, String descripcion);
}