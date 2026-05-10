package com.aerosmart.flytrack.infraestructura.controlador.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistroRequest(
    @NotBlank String email,
    @NotBlank String password,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String passportNumber
) {}