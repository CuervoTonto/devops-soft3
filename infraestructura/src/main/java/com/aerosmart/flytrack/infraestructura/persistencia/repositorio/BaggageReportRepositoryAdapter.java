package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.BaggageReport;
import com.aerosmart.flytrack.dominio.puerto.BaggageReportRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BaggageReportEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.BookingEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BaggageReportMapper;

public class BaggageReportRepositoryAdapter implements BaggageReportRepository {
  private final BaggageReportJpaRepository jpaRepository;
  private final BaggageReportMapper mapper;
  private final BookingJpaRepository bookingJpaRepository;

  public BaggageReportRepositoryAdapter(BaggageReportJpaRepository jpaRepository,
      BaggageReportMapper mapper, BookingJpaRepository bookingJpaRepository) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
    this.bookingJpaRepository = bookingJpaRepository;
  }

  @Override
  public BaggageReport guardar(BaggageReport report) {
    BookingEntity bookingEntity = bookingJpaRepository.findById(
        report.getBooking().getId()
    ).orElseThrow(() -> new IllegalArgumentException("Booking no encontrado"));

    BaggageReportEntity entity = mapper.toEntity(report, bookingEntity.getId());
    entity.setBooking(bookingEntity);

    BaggageReportEntity saved = jpaRepository.save(entity);
    report.setId(saved.getId());
    return report;
  }
}