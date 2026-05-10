package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.ItinerarioDTO;
import com.aerosmart.flytrack.dominio.entidad.Booking;
import com.aerosmart.flytrack.dominio.puerto.BookingRepository;
import com.aerosmart.flytrack.dominio.puerto.ItinerarioPuerto;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import java.util.List;
import java.util.Optional;

public class ItinerarioServicio implements ItinerarioPuerto {
  private final PassengerRepository passengerRepo;
  private final BookingRepository bookingRepo;

  public ItinerarioServicio(PassengerRepository passengerRepo, BookingRepository bookingRepo) {
    this.passengerRepo = passengerRepo;
    this.bookingRepo = bookingRepo;
  }

  @Override
  public List<Booking> consultarPorEmail(String email) {
    var passenger = passengerRepo.buscarPorEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));
    return bookingRepo.buscarPorPasajero(passenger.getId());
  }

  @Override
  public Optional<Booking> consultarPorReferencia(String referencia) {
    return bookingRepo.buscarPorReferencia(referencia);
  }

  public List<ItinerarioDTO> consultarPorEmailDTO(String email) {
    return consultarPorEmail(email).stream()
        .map(this::aDTO)
        .toList();
  }

  public Optional<ItinerarioDTO> consultarPorReferenciaDTO(String referencia) {
    return consultarPorReferencia(referencia).map(this::aDTO);
  }

  private ItinerarioDTO aDTO(Booking booking) {
    var schedule = booking.getFlightSchedule();
    var flight = schedule.getFlight();
    return new ItinerarioDTO(
        booking.getReference().value(),
        flight.getFlightNumber().value(),
        schedule.getOrigin().value(),
        schedule.getDestination().value(),
        schedule.getScheduledDeparture(),
        schedule.getScheduledArrival(),
        schedule.getCurrentGate() != null ? schedule.getCurrentGate().value() : "Por asignar",
        schedule.getStatus().name(),
        booking.getSeatAssignment()
    );
  }
}