package com.aerosmart.flytrack.aplicacion.dto;

import java.time.LocalDateTime;

public record ItinerarioDTO(
    String referenciaReserva,
    String numeroVuelo,
    String origen,
    String destino,
    LocalDateTime horaSalida,
    LocalDateTime horaLlegada,
    String puerta,
    String estado,
    String asiento
) {}