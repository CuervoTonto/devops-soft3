package com.aerosmart.flytrack.aplicacion.dto;

import java.time.LocalDateTime;

public record NotificacionDTO(
    String titulo,
    String mensaje,
    LocalDateTime enviadoEn
) {}