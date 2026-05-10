package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.PassengerEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.PassengerMapper;
import java.util.Optional;
import java.util.UUID;

public class PassengerRepositoryAdapter implements PassengerRepository {
private final PassengerJpaRepository jpaRepository;
private final PassengerMapper mapper;

public PassengerRepositoryAdapter(PassengerJpaRepository jpaRepository, PassengerMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
}

@Override
public Optional<Passenger> buscarPorId(UUID id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
}

@Override
public Optional<Passenger> buscarPorEmail(String email) {
    return jpaRepository.findByEmail(email).map(mapper::toDomain);
}

@Override
public Passenger guardar(Passenger passenger) {
    PassengerEntity entity = mapper.toEntity(passenger);
    return mapper.toDomain(jpaRepository.save(entity));
}
}