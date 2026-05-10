package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.ReporteEquipajeDTO;
import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.enums.FlightStatus;
import com.aerosmart.flytrack.dominio.excepcion.ReservaNoEncontradaException;
import com.aerosmart.flytrack.dominio.excepcion.VueloNoDisponibleException;
import com.aerosmart.flytrack.dominio.excepcion.PeriodoReporteExpiradoException;
import com.aerosmart.flytrack.dominio.puerto.BaggageReportRepository;
import com.aerosmart.flytrack.dominio.puerto.BookingRepository;
import com.aerosmart.flytrack.dominio.puerto.EquipajePuerto;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class EquipajeServicio implements EquipajePuerto {
private final BookingRepository bookingRepo;
private final BaggageReportRepository baggageReportRepo;
private final PassengerRepository passengerRepository;

public EquipajeServicio(BookingRepository bookingRepo,
    BaggageReportRepository baggageReportRepo,
    PassengerRepository passengerRepository) {
    this.bookingRepo = bookingRepo;
    this.baggageReportRepo = baggageReportRepo;
    this.passengerRepository = passengerRepository;
}

@Override
public BaggageReport reportarIncidencia(String referenciaReserva, String tipoIncidencia, String descripcion) {
    var booking = bookingRepo.buscarPorReferencia(referenciaReserva)
        .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada"));

    var flightSchedule = booking.getFlightSchedule();
    var estado = flightSchedule.getStatus();

    if (estado != FlightStatus.DEPARTED && estado != FlightStatus.LANDED) {
    throw new VueloNoDisponibleException("No puedes reportar equipaje de un vuelo que aún no ha partido");
    }

    var arrivalDate = flightSchedule.getScheduledArrival().toLocalDate();
    if (arrivalDate.isBefore(LocalDate.now().minusDays(5))) {
    throw new PeriodoReporteExpiradoException("Solo puedes reportar equipaje dentro de los 5 días posteriores a la llegada del vuelo");
    }

    var reporte = booking.reportarEquipaje(tipoIncidencia, descripcion);
    baggageReportRepo.guardar(reporte);

    return reporte;
}

public ReporteEquipajeDTO reportarIncidenciaDTO(String referenciaReserva, String tipoIncidencia, String descripcion) {
    var reporte = reportarIncidencia(referenciaReserva, tipoIncidencia, descripcion);
    return aDTO(reporte);
}

public List<ReporteEquipajeDTO> listarPorEmailDTO(String email) {
    Passenger passenger = passengerRepository.buscarPorEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));

    return baggageReportRepo.buscarPorPasajero(passenger.getId()).stream()
        .map(this::aDTO)
        .toList();
}

public List<ReporteEquipajeDTO> listarPorBookingDTO(UUID bookingId) {
    return baggageReportRepo.buscarPorBooking(bookingId).stream()
        .map(this::aDTO)
        .toList();
}

private ReporteEquipajeDTO aDTO(BaggageReport reporte) {
    var booking = reporte.getBooking();
    var flightSchedule = booking.getFlightSchedule();
    var flight = flightSchedule.getFlight();
    return new ReporteEquipajeDTO(
        reporte.getId(),
        reporte.getBooking().getId(),
        reporte.getIncidentType().name(),
        reporte.getDescription(),
        reporte.getStatus().name(),
        reporte.getReportedAt(),
        flight.getFlightNumber().value(),
        booking.getReference().value(),
        flightSchedule.getOrigin().value(),
        flightSchedule.getDestination().value()
    );
}
}