package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import java.util.List;
import java.util.UUID;

public interface BaggageReportRepository {
  BaggageReport guardar(BaggageReport report);
  List<BaggageReport> buscarPorBooking(UUID bookingId);
  List<BaggageReport> buscarPorPasajero(UUID passengerId);
}