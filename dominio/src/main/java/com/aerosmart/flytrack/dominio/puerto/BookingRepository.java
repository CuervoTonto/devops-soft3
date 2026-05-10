package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Booking;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
Optional<Booking> buscarPorId(UUID id);
Optional<Booking> buscarPorReferencia(String reference);
List<Booking> buscarPorPasajero(UUID passengerId);
Booking guardar(Booking booking);
}