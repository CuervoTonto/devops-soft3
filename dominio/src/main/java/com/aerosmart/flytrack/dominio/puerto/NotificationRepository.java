package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificationRepository {
  Notification guardar(Notification notification);
  List<Notification> buscarPorPasajero(UUID passengerId);
}