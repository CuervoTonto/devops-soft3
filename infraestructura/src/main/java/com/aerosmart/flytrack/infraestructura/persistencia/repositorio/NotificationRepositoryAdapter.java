package com.aerosmart.flytrack.infraestructura.persistencia.repositorio;

import com.aerosmart.flytrack.dominio.entidad.Notification;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.NotificationRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.NotificationEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.entidad.PassengerEntity;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.PassengerMapper;
import java.util.List;
import java.util.UUID;

public class NotificationRepositoryAdapter implements NotificationRepository {
  private final NotificationJpaRepository jpaRepository;
  private final PassengerMapper passengerMapper;
  private final PassengerJpaRepository passengerJpaRepository;

  public NotificationRepositoryAdapter(NotificationJpaRepository jpaRepository,
      PassengerMapper passengerMapper, PassengerJpaRepository passengerJpaRepository) {
    this.jpaRepository = jpaRepository;
    this.passengerMapper = passengerMapper;
    this.passengerJpaRepository = passengerJpaRepository;
  }

  @Override
  public Notification guardar(Notification notification) {
    PassengerEntity passengerEntity = passengerJpaRepository.findById(
        notification.getPassenger().getId()
    ).orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));

    NotificationEntity entity = new NotificationEntity();
    entity.setTitle(notification.getTitle());
    entity.setMessage(notification.getMessage());
    entity.setSentAt(notification.getSentAt());
    entity.setPassenger(passengerEntity);

    NotificationEntity saved = jpaRepository.save(entity);
    notification.setId(saved.getId());
    return notification;
  }

  @Override
  public List<Notification> buscarPorPasajero(UUID passengerId) {
    return jpaRepository.findByPassengerId(passengerId).stream()
        .map(this::toDomain)
        .toList();
  }

  private Notification toDomain(NotificationEntity entity) {
    Passenger passenger = passengerMapper.toDomain(entity.getPassenger());
    Notification notification = new Notification(passenger, entity.getTitle(), entity.getMessage());
    notification.setId(entity.getId());
    return notification;
  }
}