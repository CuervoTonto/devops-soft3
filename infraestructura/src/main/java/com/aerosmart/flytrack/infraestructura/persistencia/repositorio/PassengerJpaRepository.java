package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.PassengerEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerJpaRepository extends JpaRepository<PassengerEntity, UUID> {
Optional<PassengerEntity> findByEmail(String email);
}