package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.FlightSchedule;
import com.aerosmart.flytrack.dominio.puerto.FlightScheduleRepository;
import com.aerosmart.flytrack.dominio.puerto.VueloPuerto;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.FlightScheduleEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.FlightScheduleMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightScheduleRepositoryAdapter implements FlightScheduleRepository, VueloPuerto {
private final FlightScheduleJpaRepository jpaRepository;
private final FlightScheduleMapper mapper;

public FlightScheduleRepositoryAdapter(FlightScheduleJpaRepository jpaRepository,
    FlightScheduleMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
}

@Override
public FlightSchedule guardar(FlightSchedule schedule) {
    FlightScheduleEntity entity = mapper.toEntity(schedule);
    FlightScheduleEntity saved = jpaRepository.save(entity);
    return mapper.toDomain(saved);
}

@Override
public List<FlightSchedule> buscarPorVuelo(UUID flightId) {
    return jpaRepository.findByFlightId(flightId).stream()
        .map(mapper::toDomain)
        .toList();
}

@Override
public Optional<FlightSchedule> buscarPorId(UUID id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
}

@Override
public List<FlightSchedule> listarVuelosDisponibles() {
    return jpaRepository.findAll().stream()
        .map(mapper::toDomain)
        .toList();
}
}