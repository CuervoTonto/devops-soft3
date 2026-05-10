package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "baggage_reports")
public class BaggageReportEntity {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "booking_id", nullable = false)
private BookingEntity booking;

@Column(name = "incident_type", nullable = false)
private String incidentType;

@Column(columnDefinition = "TEXT")
private String description;

@Column(nullable = false)
private String status;

@Column(name = "reported_at", nullable = false)
private LocalDateTime reportedAt;

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public BookingEntity getBooking() { return booking; }
public void setBooking(BookingEntity booking) { this.booking = booking; }
public String getIncidentType() { return incidentType; }
public void setIncidentType(String incidentType) { this.incidentType = incidentType; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public LocalDateTime getReportedAt() { return reportedAt; }
public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
}