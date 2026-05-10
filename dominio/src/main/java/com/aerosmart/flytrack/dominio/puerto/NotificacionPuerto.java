package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificacionPuerto {
void notificarCambioVuelo(UUID passengerId, String titulo, String mensaje);
List<Notification> obtenerNotificaciones(UUID passengerId);
}