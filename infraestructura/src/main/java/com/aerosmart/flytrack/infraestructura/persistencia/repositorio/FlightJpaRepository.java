package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightJpaRepository extends JpaRepository<FlightEntity, UUID> {
  Optional<FlightEntity> findByFlightNumber(String flightNumber);
}