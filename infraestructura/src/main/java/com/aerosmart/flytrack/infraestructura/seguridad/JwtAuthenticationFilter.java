package com.aerosmart.flytrack.infraestructura.seguridad;

import com.aerosmart.flytrack.dominio.puerto.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  private static final List<String> RUTAS_PUBLICAS_PREFIX = List.of(
    "/api/auth/",
    "/css/",
    "/js/"
  );

  private static final List<String> RUTAS_PUBLICAS_EXACTAS = List.of(
    "/",
    "/login",
    "/registro",
    "/error"
  );

  private final TokenService tokenService;

  public JwtAuthenticationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    
    boolean esPublica = RUTAS_PUBLICAS_EXACTAS.contains(path) 
        || RUTAS_PUBLICAS_PREFIX.stream().anyMatch(path::startsWith);
    
    log.info("shouldNotFilter - Path: {}, Skip: {}", path, esPublica);
    return esPublica;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    
    log.info(">>> doFilterInternal - Request URI: {}", request.getRequestURI());
    
    String token = extraerToken(request);
    log.debug("Token extraido: {}", token != null ? "presente" : "null");

    if (token != null && tokenService.validarToken(token)) {
      String email = tokenService.obtenerEmailDelToken(token);
      log.debug("Token valido, email: {}", email);
      request.setAttribute("email", email);
    } else {
      log.debug("Token no valido o null");
    }

    filterChain.doFilter(request, response);
  }

  private String extraerToken(HttpServletRequest request) {
    if (request.getCookies() != null) {
      log.debug("Cookies encontradas: {}", request.getCookies().length);
      for (Cookie cookie : request.getCookies()) {
        log.debug("Cookie: name={}, value={}", cookie.getName(), cookie.getValue());
        if ("token".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    } else {
      log.debug("No hay cookies en la peticion");
    }
    return null;
  }
}