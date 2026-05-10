package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.VueloDTO;
import com.aerosmart.flytrack.dominio.entidad.FlightSchedule;
import com.aerosmart.flytrack.dominio.puerto.VueloPuerto;
import java.util.List;
import java.util.Optional;

public class VueloServicio {
private final VueloPuerto vueloRepository;

public VueloServicio(VueloPuerto vueloRepository) {
    this.vueloRepository = vueloRepository;
}

public List<FlightSchedule> listarVuelosDisponibles() {
    return vueloRepository.listarVuelosDisponibles();
}

public Optional<FlightSchedule> buscarPorId(java.util.UUID id) {
    return vueloRepository.buscarPorId(id);
}

public List<VueloDTO> listarVuelosDisponiblesDTO() {
    return listarVuelosDisponibles().stream()
        .filter(fs -> fs.getScheduledDeparture().isAfter(java.time.LocalDateTime.now()))
        .map(this::aDTO)
        .toList();
}

public VueloDTO buscarPorIdDTO(java.util.UUID id) {
    return buscarPorId(id).map(this::aDTO).orElse(null);
}

private VueloDTO aDTO(FlightSchedule fs) {
    var flight = fs.getFlight();
    return new VueloDTO(
        fs.getId(),
        flight.getFlightNumber().value(),
        flight.getAirlineName(),
        fs.getOrigin().value(),
        fs.getDestination().value(),
        fs.getScheduledDeparture(),
        fs.getScheduledArrival(),
        fs.getCurrentGate() != null ? fs.getCurrentGate().value() : "Por asignar",
        fs.getStatus().name()
    );
}
}