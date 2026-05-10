package com.aerosmart.flytrack.aplicacion.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VueloDTO(
    UUID id,
    String numeroVuelo,
    String aerolinea,
    String origen,
    String destino,
    LocalDateTime salida,
    LocalDateTime llegada,
    String puerta,
    String estado
) {}