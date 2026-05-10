package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "flights")
public class FlightEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "flight_number", nullable = false, unique = true)
  private String flightNumber;

  @Column(name = "airline_name", nullable = false)
  private String airlineName;

  @Column(name = "aircraft_type")
  private String aircraftType;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getFlightNumber() { return flightNumber; }
  public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
  public String getAirlineName() { return airlineName; }
  public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
  public String getAircraftType() { return aircraftType; }
  public void setAircraftType(String aircraftType) { this.aircraftType = aircraftType; }
}