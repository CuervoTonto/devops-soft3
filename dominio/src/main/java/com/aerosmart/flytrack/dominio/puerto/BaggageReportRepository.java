package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;

public interface BaggageReportRepository {
  BaggageReport guardar(BaggageReport report);
}