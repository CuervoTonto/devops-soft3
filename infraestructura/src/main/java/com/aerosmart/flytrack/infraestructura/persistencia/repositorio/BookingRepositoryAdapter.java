package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.Booking;
import com.aerosmart.flytrack.dominio.entidad.FlightSchedule;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.BookingRepository;
import com.aerosmart.flytrack.dominio.puerto.FlightScheduleRepository;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import com.aerosmart.flytrack.dominio.puerto.ReservaPuerto;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BookingMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingRepositoryAdapter implements BookingRepository, ReservaPuerto {
private final BookingJpaRepository jpaRepository;
private final BookingMapper mapper;
private final PassengerRepository passengerRepository;
private final FlightScheduleRepository flightScheduleRepository;

public BookingRepositoryAdapter(BookingJpaRepository jpaRepository,
    BookingMapper mapper,
    PassengerRepository passengerRepository,
    FlightScheduleRepository flightScheduleRepository) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
    this.passengerRepository = passengerRepository;
    this.flightScheduleRepository = flightScheduleRepository;
}

@Override
public Optional<Booking> buscarPorId(UUID id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
}

@Override
public Optional<Booking> buscarPorReferencia(String reference) {
    return jpaRepository.findByBookingReference(reference).map(mapper::toDomain);
}

@Override
public List<Booking> buscarPorPasajero(UUID passengerId) {
    return jpaRepository.findByPassengerId(passengerId).stream()
        .map(mapper::toDomain)
        .toList();
}

@Override
public Booking guardar(Booking booking) {
    BookingEntity entity = mapper.toEntity(booking);
    BookingEntity saved = jpaRepository.save(entity);
    booking.setId(saved.getId());
    return booking;
}

@Override
public Booking crearReserva(UUID passengerId, UUID flightScheduleId, String seat) {
    Passenger passenger = passengerRepository.buscarPorId(passengerId)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));

    FlightSchedule schedule = flightScheduleRepository.buscarPorId(flightScheduleId)
        .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));

    Booking booking = new Booking(passenger, schedule, seat);
    return guardar(booking);
}
}