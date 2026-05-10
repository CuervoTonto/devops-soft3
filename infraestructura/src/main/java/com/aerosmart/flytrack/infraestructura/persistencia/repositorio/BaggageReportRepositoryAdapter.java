package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.dominio.puerto.BaggageReportRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BaggageReportMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BookingMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.FlightScheduleMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.PassengerMapper;
import java.util.List;
import java.util.UUID;

public class BaggageReportRepositoryAdapter implements BaggageReportRepository {
  private final BaggageReportJpaRepository jpaRepository;
  private final BaggageReportMapper baggageMapper;
  private final BookingJpaRepository bookingJpaRepository;
  private final BookingMapper bookingMapper;

  public BaggageReportRepositoryAdapter(BaggageReportJpaRepository jpaRepository,
      BaggageReportMapper baggageMapper, BookingJpaRepository bookingJpaRepository,
      BookingMapper bookingMapper) {
    this.jpaRepository = jpaRepository;
    this.baggageMapper = baggageMapper;
    this.bookingJpaRepository = bookingJpaRepository;
    this.bookingMapper = bookingMapper;
  }

  @Override
  public BaggageReport guardar(BaggageReport report) {
    BookingEntity bookingEntity = bookingJpaRepository.findById(
        report.getBooking().getId()
    ).orElseThrow(() -> new IllegalArgumentException("Booking no encontrado"));

    BaggageReportEntity entity = baggageMapper.toEntity(report, bookingEntity.getId());
    entity.setBooking(bookingEntity);

    BaggageReportEntity saved = jpaRepository.save(entity);
    report.setId(saved.getId());
    return report;
  }

  @Override
  public List<BaggageReport> buscarPorBooking(UUID bookingId) {
    return jpaRepository.findByBookingIdOrderByReportedAtDesc(bookingId).stream()
        .map(entity -> baggageMapper.toDomain(entity, bookingMapper))
        .toList();
  }

  @Override
  public List<BaggageReport> buscarPorPasajero(UUID passengerId) {
    return jpaRepository.findByPassengerId(passengerId).stream()
        .map(entity -> baggageMapper.toDomain(entity, bookingMapper))
        .toList();
  }
}