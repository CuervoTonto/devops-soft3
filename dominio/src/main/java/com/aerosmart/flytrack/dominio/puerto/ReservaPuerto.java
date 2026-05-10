package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Booking;
import java.util.UUID;

public interface ReservaPuerto {
Booking crearReserva(UUID passengerId, UUID flightScheduleId, String seat);
}