package com.aerosmart.flytrack.infraestructura.persistencia.mapper;

import com.aerosmart.flytrack.dominio.entidad.Booking;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.valueobject.BookingReference;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightScheduleEntity;
import java.time.LocalDateTime;

public class BookingMapper {
  private final FlightScheduleMapper flightScheduleMapper;
  private final PassengerMapper passengerMapper;

  public BookingMapper(FlightScheduleMapper flightScheduleMapper, PassengerMapper passengerMapper) {
    this.flightScheduleMapper = flightScheduleMapper;
    this.passengerMapper = passengerMapper;
  }

  public Booking toDomain(BookingEntity entity) {
    Passenger passenger = passengerMapper.toDomain(entity.getPassenger());
    FlightScheduleEntity scheduleEntity = entity.getFlightSchedule();
    
    var flightSchedule = flightScheduleMapper.toDomainConRelaciones(
        scheduleEntity.getId(),
        scheduleEntity.getOriginIata(),
        scheduleEntity.getDestinationIata(),
        scheduleEntity.getDepartureTime(),
        scheduleEntity.getArrivalTime(),
        scheduleEntity.getCurrentGate(),
        scheduleEntity.getStatus()
    );
    flightSchedule.setId(scheduleEntity.getId());

    Booking booking = new Booking(passenger, flightSchedule, entity.getSeatAssignment());
    booking.setId(entity.getId());
    return booking;
  }

  public BookingEntity toEntity(Booking booking) {
    BookingEntity entity = new BookingEntity();
    
    var passengerMapper = this.passengerMapper;
    var passengerEntity = new com.aerosmart.flytrack.infraestructura.persistencia.entidad.PassengerEntity();
    passengerEntity.setId(booking.getPassenger().getId());
    entity.setPassenger(passengerEntity);
    
    var fsEntity = new com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightScheduleEntity();
    fsEntity.setId(booking.getFlightSchedule().getId());
    entity.setFlightSchedule(fsEntity);
    
    entity.setSeatAssignment(booking.getSeatAssignment());
    entity.setBookingReference(booking.getReference().value());
    entity.setCreatedAt(booking.getCreatedAt());
    if (booking.getId() != null) {
      entity.setId(booking.getId());
    }
    return entity;
  }
}