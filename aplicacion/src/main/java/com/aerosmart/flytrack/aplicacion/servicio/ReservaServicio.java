package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.ReservaDTO;
import com.aerosmart.flytrack.dominio.entidad.Booking;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import com.aerosmart.flytrack.dominio.puerto.ReservaPuerto;
import java.util.UUID;

public class ReservaServicio {
private final ReservaPuerto reservaRepository;
private final PassengerRepository passengerRepository;

public ReservaServicio(ReservaPuerto reservaRepository, PassengerRepository passengerRepository) {
    this.reservaRepository = reservaRepository;
    this.passengerRepository = passengerRepository;
}

public Booking crearReserva(UUID passengerId, UUID flightScheduleId, String seat) {
    return reservaRepository.crearReserva(passengerId, flightScheduleId, seat);
}

public ReservaDTO crearReservaDTO(UUID passengerId, UUID flightScheduleId) {
    String asientoAleatorio = generarAsientoAleatorio();
    Booking booking = crearReserva(passengerId, flightScheduleId, asientoAleatorio);
    return aDTO(booking);
}

public ReservaDTO crearReservaDTO(String email, UUID flightScheduleId) {
    Passenger passenger = passengerRepository.buscarPorEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));
    return crearReservaDTO(passenger.getId(), flightScheduleId);
}

private String generarAsientoAleatorio() {
    String[] letras = {"A", "B", "C", "D", "E", "F"};
    int numero = (int) (Math.random() * 30) + 1;
    String letra = letras[(int) (Math.random() * letras.length)];
    return numero + letra;
}

private ReservaDTO aDTO(Booking booking) {
    var schedule = booking.getFlightSchedule();
    var flight = schedule.getFlight();
    return new ReservaDTO(
        booking.getReference().value(),
        flight.getFlightNumber().value(),
        schedule.getOrigin().value(),
        schedule.getDestination().value(),
        schedule.getScheduledDeparture(),
        booking.getSeatAssignment()
    );
}
}