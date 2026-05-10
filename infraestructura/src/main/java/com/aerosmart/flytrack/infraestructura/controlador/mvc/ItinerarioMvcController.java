package com.aerosmart.flytrack.infraestructura.controlador.mvc;

import com.aerosmart.flytrack.aplicacion.dto.ItinerarioDTO;
import com.aerosmart.flytrack.aplicacion.servicio.ItinerarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itinerario")
public class ItinerarioMvcController {
private final ItinerarioServicio servicio;

public ItinerarioMvcController(ItinerarioServicio servicio) {
    this.servicio = servicio;
}

@GetMapping
public String verItinerario(HttpServletRequest request, Model model) {
    String email = (String) request.getAttribute("email");
    if (email != null) {
    List<ItinerarioDTO> itinerarios = servicio.consultarPorEmailDTO(email);
    model.addAttribute("itinerarios", itinerarios);
    model.addAttribute("email", email);
    }
    return "itinerario";
}

@GetMapping("/detalle/{referencia}")
public String detalle(@PathVariable String referencia, Model model) {
    Optional<ItinerarioDTO> dto = servicio.consultarPorReferenciaDTO(referencia);
    if (dto.isPresent()) {
    model.addAttribute("itinerario", dto.get());
    }
    return "itinerario-detalle";
}
}