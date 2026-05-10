package com.aerosmart.flytrack.infraestructura.controlador.rest;

import com.aerosmart.flytrack.aplicacion.dto.VueloDTO;
import com.aerosmart.flytrack.aplicacion.servicio.VueloServicio;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {
  private final VueloServicio servicio;

  public VueloController(VueloServicio servicio) {
    this.servicio = servicio;
  }

  @GetMapping
  public List<VueloDTO> listarVuelos() {
    return servicio.listarVuelosDisponiblesDTO();
  }

  @GetMapping("/{id}")
  public VueloDTO obtenerVuelo(@PathVariable java.util.UUID id) {
    return servicio.buscarPorIdDTO(id);
  }
}