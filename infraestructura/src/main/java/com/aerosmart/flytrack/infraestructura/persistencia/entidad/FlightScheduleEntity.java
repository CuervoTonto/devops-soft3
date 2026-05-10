package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flight_schedules")
public class FlightScheduleEntity {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "flight_id", nullable = false)
private FlightEntity flight;

@Column(name = "origin_iata", nullable = false)
private String originIata;

@Column(name = "destination_iata", nullable = false)
private String destinationIata;

@Column(name = "departure_time", nullable = false)
private LocalDateTime departureTime;

@Column(name = "arrival_time", nullable = false)
private LocalDateTime arrivalTime;

@Column(name = "current_gate")
private String currentGate;

@Column(nullable = false)
private String status;

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public FlightEntity getFlight() { return flight; }
public void setFlight(FlightEntity flight) { this.flight = flight; }
public String getOriginIata() { return originIata; }
public void setOriginIata(String originIata) { this.originIata = originIata; }
public String getDestinationIata() { return destinationIata; }
public void setDestinationIata(String destinationIata) { this.destinationIata = destinationIata; }
public LocalDateTime getDepartureTime() { return departureTime; }
public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
public LocalDateTime getArrivalTime() { return arrivalTime; }
public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
public String getCurrentGate() { return currentGate; }
public void setCurrentGate(String currentGate) { this.currentGate = currentGate; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
}