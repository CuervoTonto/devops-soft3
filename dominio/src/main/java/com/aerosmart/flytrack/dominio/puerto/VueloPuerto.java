package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.FlightSchedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VueloPuerto {
List<FlightSchedule> listarVuelosDisponibles();
Optional<FlightSchedule> buscarPorId(UUID id);
}