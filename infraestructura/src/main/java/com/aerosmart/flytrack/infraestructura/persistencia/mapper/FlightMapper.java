package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.Flight;
import com.aerosmart.flytrack.dominio.valueobject.FlightNumber;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightEntity;

public class FlightMapper {

public Flight toDomain(FlightEntity entity) {
    Flight flight = new Flight(
        new FlightNumber(entity.getFlightNumber()),
        entity.getAirlineName(),
        entity.getAircraftType()
    );
    flight.setId(entity.getId());
    return flight;
}

public FlightEntity toEntity(Flight flight) {
    FlightEntity entity = new FlightEntity();
    if (flight.getId() != null) {
    entity.setId(flight.getId());
    }
    entity.setFlightNumber(flight.getFlightNumber().value());
    entity.setAirlineName(flight.getAirlineName());
    entity.setAircraftType(flight.getAircraftType());
    return entity;
}
}