package com.aerosmart.flytrack.dominio.puerto;

import com.aerosmart.flytrack.dominio.entidad.Passenger;

public interface AutenticacionPuerto {
String login(String email, String password);
String getEmailFromToken(String token);
Passenger registrar(RegistroCommand command);
}