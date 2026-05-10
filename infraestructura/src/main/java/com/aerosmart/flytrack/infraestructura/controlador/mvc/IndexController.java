package com.aerosmart.flytrack.infraestructura.controlador.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/registro")
  public String registro() {
    return "registro";
  }

  @GetMapping("/equipaje")
  public String equipaje() {
    return "equipaje";
  }
}