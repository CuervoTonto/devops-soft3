package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Booking;
import java.util.List;
import java.util.Optional;

public interface ItinerarioPuerto {
List<Booking> consultarPorEmail(String email);
Optional<Booking> consultarPorReferencia(String referencia);
}