package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.aplicacion.dto.NotificacionDTO;
import com.aerosmart.flytrack.dominio.entidad.Notification;
import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.NotificationRepository;
import com.aerosmart.flytrack.dominio.puerto.NotificacionPuerto;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import java.util.List;
import java.util.UUID;

public class NotificacionServicio implements NotificacionPuerto {
private final NotificationRepository notificationRepo;
private final PassengerRepository passengerRepo;

public NotificacionServicio(NotificationRepository notificationRepo, PassengerRepository passengerRepo) {
    this.notificationRepo = notificationRepo;
    this.passengerRepo = passengerRepo;
}

public Passenger buscarPasajeroPorEmail(String email) {
    return passengerRepo.buscarPorEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));
}

@Override
public void notificarCambioVuelo(UUID passengerId, String titulo, String mensaje) {
    var passenger = passengerRepo.buscarPorId(passengerId)
        .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));

    var notification = new Notification(passenger, titulo, mensaje);
    notificationRepo.guardar(notification);
}

@Override
public List<Notification> obtenerNotificaciones(UUID passengerId) {
    return notificationRepo.buscarPorPasajero(passengerId);
}

public List<NotificacionDTO> obtenerNotificacionesDTO(UUID passengerId) {
    return obtenerNotificaciones(passengerId).stream()
        .map(n -> new NotificacionDTO(n.getTitle(), n.getMessage(), n.getSentAt()))
        .toList();
}
}