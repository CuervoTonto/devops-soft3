package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import java.util.UUID;

public class BaggageReportMapper {

public BaggageReport toDomain(BaggageReportEntity entity, BookingMapper bookingMapper) {
    var booking = bookingMapper.toDomain(entity.getBooking());

    BaggageReport report = new BaggageReport(
        booking,
        entity.getIncidentType(),
        entity.getDescription()
    );
    report.setId(entity.getId());
    return report;
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