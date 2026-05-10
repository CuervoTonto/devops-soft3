package com.aerosmart.flytrack.dominio.entidad;

import com.aerosmart.flytrack.dominio.enums.FlightStatus;
import com.aerosmart.flytrack.dominio.valueobject.Gate;
import com.aerosmart.flytrack.dominio.valueobject.IATACode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlightSchedule {
  private UUID id;
  private Flight flight;
  private IATACode origin;
  private IATACode destination;
  private LocalDateTime scheduledDeparture;
  private LocalDateTime scheduledArrival;
  private Gate currentGate;
  private FlightStatus status;
  private List<Booking> bookings = new ArrayList<>();

  public FlightSchedule(Flight flight, IATACode origin, IATACode destination,
                        LocalDateTime departure, LocalDateTime arrival) {
    this.flight = flight;
    this.origin = origin;
    this.destination = destination;
    this.scheduledDeparture = departure;
    this.scheduledArrival = arrival;
    this.status = FlightStatus.SCHEDULED;
  }

  public void asignarPuerta(Gate gate) {
    this.currentGate = gate;
    if (status == FlightStatus.ON_TIME || status == FlightStatus.DELAYED) {
      this.status = FlightStatus.BOARDING;
    }
  }

  public void retraso(LocalDateTime nuevaLlegada) {
    this.scheduledArrival = nuevaLlegada;
    if (status == FlightStatus.ON_TIME) {
      this.status = FlightStatus.DELAYED;
    }
  }

  public void cancelar() {
    this.status = FlightStatus.CANCELLED;
  }

  public boolean tieneRetraso() {
    return status == FlightStatus.DELAYED;
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public void setFlight(Flight flight) { this.flight = flight; }
  public Flight getFlight() { return flight; }
  public IATACode getOrigin() { return origin; }
  public IATACode getDestination() { return destination; }
  public LocalDateTime getScheduledDeparture() { return scheduledDeparture; }
  public LocalDateTime getScheduledArrival() { return scheduledArrival; }
  public Gate getCurrentGate() { return currentGate; }
  public FlightStatus getStatus() { return status; }
  public List<Booking> getBookings() { return bookings; }
}