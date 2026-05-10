package com.aerosmart.flytrack.infraestructura.seguridad;

import com.aerosmart.flytrack.dominio.puerto.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtTokenServiceAdapter implements TokenService {
  private final String secret;
  private final long expiracionMs;

  public JwtTokenServiceAdapter(String secret) {
    this(secret, 86400000L);
  }

  public JwtTokenServiceAdapter(String secret, long expiracionMs) {
    this.secret = secret;
    this.expiracionMs = expiracionMs;
  }

  @Override
  public String generarToken(String email) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiracionMs))
        .signWith(key)
        .compact();
  }

  @Override
  public String obtenerEmailDelToken(String token) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  @Override
  public boolean validarToken(String token) {
    try {
      obtenerEmailDelToken(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}