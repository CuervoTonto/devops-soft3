package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Passenger;
import java.util.Optional;
import java.util.UUID;

public interface PassengerRepository {
  Optional<Passenger> buscarPorId(UUID id);
  Optional<Passenger> buscarPorEmail(String email);
  Passenger guardar(Passenger passenger);
}