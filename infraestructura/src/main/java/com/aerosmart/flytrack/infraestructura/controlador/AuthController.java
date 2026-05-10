package com.aerosmart.flytrack.infraestructura.controlador;

import com.aerosmart.flytrack.aplicacion.servicio.AutenticacionServicio;
import com.aerosmart.flytrack.dominio.puerto.RegistroCommand;
import com.aerosmart.flytrack.infraestructura.controlador.dto.RegistroRequest;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AutenticacionServicio servicio;

  public AuthController(AutenticacionServicio servicio) {
    this.servicio = servicio;
  }

  @PostMapping("/login")
  public Map<String, String> login(@Valid @RequestBody LoginRequest request) {
    String token = servicio.login(request.email(), request.password());
    return Map.of("token", token);
  }

  @PostMapping("/registro")
  public Map<String, String> registro(@Valid @RequestBody RegistroRequest request) {
    RegistroCommand command = new RegistroCommand(
        request.email(),
        request.password(),
        request.firstName(),
        request.lastName(),
        request.passportNumber()
    );
    servicio.registrar(command);
    String token = servicio.login(request.email(), request.password());
    return Map.of("token", token);
  }

  public record LoginRequest(String email, String password) {}
}