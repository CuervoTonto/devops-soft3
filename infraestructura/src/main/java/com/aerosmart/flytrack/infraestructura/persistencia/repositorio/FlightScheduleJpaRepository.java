package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightScheduleEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightScheduleJpaRepository extends JpaRepository<FlightScheduleEntity, UUID> {
List<FlightScheduleEntity> findByFlightId(UUID flightId);
}