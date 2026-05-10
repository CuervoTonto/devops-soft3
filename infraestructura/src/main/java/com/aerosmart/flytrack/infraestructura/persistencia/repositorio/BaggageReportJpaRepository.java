package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageReportJpaRepository extends JpaRepository<BaggageReportEntity, UUID> {
  List<BaggageReportEntity> findByBookingId(UUID bookingId);
  List<BaggageReportEntity> findByBookingIdOrderByReportedAtDesc(UUID bookingId);
  List<BaggageReportEntity> findByStatus(String status);
}