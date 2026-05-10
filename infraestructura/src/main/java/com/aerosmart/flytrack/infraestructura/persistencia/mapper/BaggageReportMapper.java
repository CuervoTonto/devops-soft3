package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.dominio.entidad.Booking;
import com.aerosmart.flytrack.dominio.enums.IncidentType;
import com.aerosmart.flytrack.dominio.enums.ReportStatus;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import java.util.UUID;

public class BaggageReportMapper {

  public BaggageReport toDomain(BaggageReportEntity entity) {
    BookingEntity bookingEntity = entity.getBooking();
    Booking booking = new Booking(
        null,
        null,
        bookingEntity.getSeatAssignment()
    );

    return new BaggageReport(
        booking,
        entity.getIncidentType(),
        entity.getDescription()
    );
  }

  public BaggageReportEntity toEntity(BaggageReport report, UUID bookingId) {
    BaggageReportEntity entity = new BaggageReportEntity();
    entity.setIncidentType(report.getIncidentType().name());
    entity.setDescription(report.getDescription());
    entity.setStatus(report.getStatus().name());
    entity.setReportedAt(report.getReportedAt());
    return entity;
  }
}