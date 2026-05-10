package com.aerosmart.flytrack.dominio.entidad;

import com.aerosmart.flytrack.dominio.valueobject.BookingReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
private UUID id;
private Passenger passenger;
private FlightSchedule flightSchedule;
private String seatAssignment;
private BookingReference reference;
private LocalDateTime createdAt;
private List<BaggageReport> baggageReports = new ArrayList<>();

public Booking(Passenger passenger, FlightSchedule flightSchedule, String seatAssignment) {
    this.passenger = passenger;
    this.flightSchedule = flightSchedule;
    this.seatAssignment = seatAssignment;
    this.reference = new BookingReference(generarReferencia());
    this.createdAt = LocalDateTime.now();
}

private String generarReferencia() {
    return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
}

public BaggageReport reportarEquipaje(String tipoIncidencia, String descripcion) {
    BaggageReport reporte = new BaggageReport(this, tipoIncidencia, descripcion);
    this.baggageReports.add(reporte);
    return reporte;
}

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public void setPassenger(Passenger passenger) { this.passenger = passenger; }
public void setFlightSchedule(FlightSchedule flightSchedule) { this.flightSchedule = flightSchedule; }
public BookingReference getReference() { return reference; }
public void setReference(BookingReference reference) { this.reference = reference; }
public FlightSchedule getFlightSchedule() { return flightSchedule; }
public Passenger getPassenger() { return passenger; }
public String getSeatAssignment() { return seatAssignment; }
public LocalDateTime getCreatedAt() { return createdAt; }
public List<BaggageReport> getBaggageReports() { return baggageReports; }
}