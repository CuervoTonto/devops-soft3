package com.aerosmart.flytrack.aplicacion.dto;

import java.time.LocalDateTime;

public record ReservaDTO(
    String referencia,
    String numeroVuelo,
    String origen,
    String destino,
    LocalDateTime salida,
    String asiento
) {}