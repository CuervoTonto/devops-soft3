package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.Flight;
import com.aerosmart.flytrack.dominio.entidad.FlightSchedule;
import com.aerosmart.flytrack.dominio.enums.FlightStatus;
import com.aerosmart.flytrack.dominio.valueobject.FlightNumber;
import com.aerosmart.flytrack.dominio.valueobject.Gate;
import com.aerosmart.flytrack.dominio.valueobject.IATACode;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightScheduleEntity;
import java.time.LocalDateTime;
import java.util.UUID;

public class FlightScheduleMapper {

  public FlightSchedule toDomain(FlightScheduleEntity entity) {
    FlightEntity flightEntity = entity.getFlight();
    Flight flight = new Flight(
        new FlightNumber(flightEntity.getFlightNumber()),
        flightEntity.getAirlineName(),
        flightEntity.getAircraftType()
    );
    flight.setId(flightEntity.getId());

    IATACode origin = new IATACode(entity.getOriginIata());
    IATACode destination = new IATACode(entity.getDestinationIata());

    FlightSchedule schedule = new FlightSchedule(
        flight, origin, destination,
        entity.getDepartureTime(), entity.getArrivalTime()
    );
    schedule.setId(entity.getId());

    if (entity.getCurrentGate() != null) {
      schedule.asignarPuerta(new Gate(entity.getCurrentGate()));
    }

    schedule.retraso(entity.getArrivalTime());
    if (entity.getStatus().equals("CANCELLED")) {
      schedule.cancelar();
    }

    return schedule;
  }

  public FlightSchedule toDomainConRelaciones(UUID flightId, String originIata, 
      String destIata, LocalDateTime departure, LocalDateTime arrival, 
      String gate, String status) {
    
    Flight flight = new Flight(
        new FlightNumber("XX0000"),
        "Aerolinea",
        "Boeing 737"
    );

    FlightSchedule schedule = new FlightSchedule(
        flight,
        new IATACode(originIata),
        new IATACode(destIata),
        departure,
        arrival
    );

    if (gate != null) {
      schedule.asignarPuerta(new Gate(gate));
    }

    if (status != null) {
      try {
        FlightStatus flightStatus = FlightStatus.valueOf(status);
        if (flightStatus == FlightStatus.DELAYED) {
          schedule.retraso(arrival);
        } else if (flightStatus == FlightStatus.CANCELLED) {
          schedule.cancelar();
        }
      } catch (IllegalArgumentException e) {
      }
    }

    return schedule;
  }

  public FlightScheduleEntity toEntity(FlightSchedule schedule) {
    FlightScheduleEntity entity = new FlightScheduleEntity();
    entity.setOriginIata(schedule.getOrigin().value());
    entity.setDestinationIata(schedule.getDestination().value());
    entity.setDepartureTime(schedule.getScheduledDeparture());
    entity.setArrivalTime(schedule.getScheduledArrival());
    if (schedule.getCurrentGate() != null) {
      entity.setCurrentGate(schedule.getCurrentGate().value());
    }
    entity.setStatus(schedule.getStatus().name());
    return entity;
  }
}