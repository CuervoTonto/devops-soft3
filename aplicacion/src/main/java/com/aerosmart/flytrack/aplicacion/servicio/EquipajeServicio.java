package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.ReporteEquipajeDTO;
import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.dominio.puerto.BaggageReportRepository;
import com.aerosmart.flytrack.dominio.puerto.BookingRepository;
import com.aerosmart.flytrack.dominio.puerto.EquipajePuerto;
import java.util.UUID;

public class EquipajeServicio implements EquipajePuerto {
  private final BookingRepository bookingRepo;
  private final BaggageReportRepository baggageReportRepo;

  public EquipajeServicio(BookingRepository bookingRepo, BaggageReportRepository baggageReportRepo) {
    this.bookingRepo = bookingRepo;
    this.baggageReportRepo = baggageReportRepo;
  }

  @Override
  public BaggageReport reportarIncidencia(UUID bookingId, String tipoIncidencia, String descripcion) {
    var booking = bookingRepo.buscarPorId(bookingId)
        .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
    
    var reporte = booking.reportarEquipaje(tipoIncidencia, descripcion);
    baggageReportRepo.guardar(reporte);
    
    return reporte;
  }

  public ReporteEquipajeDTO reportarIncidenciaDTO(UUID bookingId, String tipoIncidencia, String descripcion) {
    var reporte = reportarIncidencia(bookingId, tipoIncidencia, descripcion);
    return aDTO(reporte);
  }

  private ReporteEquipajeDTO aDTO(BaggageReport reporte) {
    return new ReporteEquipajeDTO(
        reporte.getId(),
        reporte.getBooking().getId(),
        reporte.getIncidentType().name(),
        reporte.getDescription(),
        reporte.getStatus().name(),
        reporte.getReportedAt()
    );
  }
}