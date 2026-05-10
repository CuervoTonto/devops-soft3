package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {
  Optional<BookingEntity> findByBookingReference(String reference);
  List<BookingEntity> findByPassengerId(UUID passengerId);
}