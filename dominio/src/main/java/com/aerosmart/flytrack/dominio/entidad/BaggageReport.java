package com.aerosmart.flytrack.dominio.entidad;

import com.aerosmart.flytrack.dominio.enums.IncidentType;
import com.aerosmart.flytrack.dominio.enums.ReportStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class BaggageReport {
private UUID id;
private Booking booking;
private IncidentType incidentType;
private String description;
private ReportStatus status;
private LocalDateTime reportedAt;

public BaggageReport(Booking booking, String incidentType, String description) {
    this.booking = booking;
    this.incidentType = IncidentType.valueOf(incidentType.toUpperCase());
    this.description = description;
    this.status = ReportStatus.OPEN;
    this.reportedAt = LocalDateTime.now();
}

public void resolver() { this.status = ReportStatus.RESOLVED; }
public void cerrar() { this.status = ReportStatus.CLOSED; }
public void enProgreso() { this.status = ReportStatus.IN_PROGRESS; }

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public void setBooking(Booking booking) { this.booking = booking; }
public Booking getBooking() { return booking; }
public IncidentType getIncidentType() { return incidentType; }
public String getDescription() { return description; }
public ReportStatus getStatus() { return status; }
public LocalDateTime getReportedAt() { return reportedAt; }
}