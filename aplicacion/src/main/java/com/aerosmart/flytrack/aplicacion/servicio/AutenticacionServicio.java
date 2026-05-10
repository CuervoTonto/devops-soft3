package com.aerosmart.flytrack.aplicacion.servicio;

import com.aerosmart.flytrack.dominio.entidad.Passenger;
import com.aerosmart.flytrack.dominio.puerto.AutenticacionPuerto;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import com.aerosmart.flytrack.dominio.puerto.PasswordEncoderPuerto;
import com.aerosmart.flytrack.dominio.puerto.RegistroCommand;
import com.aerosmart.flytrack.dominio.puerto.TokenService;
import com.aerosmart.flytrack.dominio.valueobject.Email;

public class AutenticacionServicio implements AutenticacionPuerto {
private final TokenService tokenService;
private final PassengerRepository passengerRepo;
private final PasswordEncoderPuerto passwordEncoder;

public AutenticacionServicio(TokenService tokenService, PassengerRepository passengerRepo, PasswordEncoderPuerto passwordEncoder) {
    this.tokenService = tokenService;
    this.passengerRepo = passengerRepo;
    this.passwordEncoder = passwordEncoder;
}

@Override
public String login(String email, String password) {
    Passenger passenger = passengerRepo.buscarPorEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

    if (!passwordEncoder.matches(password, passenger.getPassword())) {
    throw new IllegalArgumentException("Credenciales invalidas");
    }

    return tokenService.generarToken(email);
}

@Override
public String getEmailFromToken(String token) {
    if (!tokenService.validarToken(token)) {
    throw new IllegalArgumentException("Token invalido");
    }
    return tokenService.obtenerEmailDelToken(token);
}

@Override
public Passenger registrar(RegistroCommand command) {
    command.validar();

    if (passengerRepo.buscarPorEmail(command.email()).isPresent()) {
    throw new IllegalArgumentException("El email ya esta registrado");
    }

    String hashedPassword = passwordEncoder.encode(command.password());
    Passenger passenger = new Passenger(
        command.firstName(),
        command.lastName(),
        new Email(command.email()),
        command.passportNumber(),
        hashedPassword
    );

    return passengerRepo.guardar(passenger);
}
}