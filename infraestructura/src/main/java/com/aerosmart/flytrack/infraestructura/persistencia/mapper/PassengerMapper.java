package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.valueobject.Email;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.PassengerEntity;

public class PassengerMapper {

  public Passenger toDomain(PassengerEntity entity) {
    Passenger passenger = new Passenger(
        entity.getFirstName(),
        entity.getLastName(),
        new Email(entity.getEmail()),
        entity.getPassportNumber(),
        entity.getPassword()
    );
    passenger.setId(entity.getId());
    return passenger;
  }

  public PassengerEntity toEntity(Passenger passenger) {
    PassengerEntity entity = new PassengerEntity();
    if (passenger.getId() != null) {
      entity.setId(passenger.getId());
    }
    entity.setFirstName(passenger.getFirstName());
    entity.setLastName(passenger.getLastName());
    entity.setEmail(passenger.getEmail().value());
    entity.setPassportNumber(passenger.getPassportNumber());
    entity.setPassword(passenger.getPassword());
    return entity;
  }
}