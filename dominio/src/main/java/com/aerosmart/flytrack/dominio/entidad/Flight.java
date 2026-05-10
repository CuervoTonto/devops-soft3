package com.aerosmart.flytrack.dominio.entidad;

import com.aerosmart.flytrack.dominio.valueobject.FlightNumber;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Flight {
private UUID id;
private FlightNumber flightNumber;
private String airlineName;
private String aircraftType;
private List<FlightSchedule> schedules = new ArrayList<>();

public Flight(FlightNumber flightNumber, String airlineName, String aircraftType) {
    this.flightNumber = flightNumber;
    this.airlineName = airlineName;
    this.aircraftType = aircraftType;
}

public void agregarHorario(FlightSchedule schedule) {
    this.schedules.add(schedule);
}

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public FlightNumber getFlightNumber() { return flightNumber; }
public String getAirlineName() { return airlineName; }
public String getAircraftType() { return aircraftType; }
public List<FlightSchedule> getSchedules() { return schedules; }
}