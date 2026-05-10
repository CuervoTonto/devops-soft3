package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaggageReportJpaRepository extends JpaRepository<BaggageReportEntity, UUID> {
  List<BaggageReportEntity> findByBookingId(UUID bookingId);
  List<BaggageReportEntity> findByBookingIdOrderByReportedAtDesc(UUID bookingId);
  List<BaggageReportEntity> findByStatus(String status);
  
  @Query("SELECT br FROM BaggageReportEntity br WHERE br.booking.passenger.id = :passengerId ORDER BY br.reportedAt DESC")
  List<BaggageReportEntity> findByPassengerId(@Param("passengerId") UUID passengerId);
}