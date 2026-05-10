package com.aerosmart.flytrack.dominio.entidad;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
  private UUID id;
  private Passenger passenger;
  private String title;
  private String message;
  private LocalDateTime sentAt;

  public Notification(Passenger passenger, String title, String message) {
    this.passenger = passenger;
    this.title = title;
    this.message = message;
    this.sentAt = LocalDateTime.now();
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public void setPassenger(Passenger passenger) { this.passenger = passenger; }
  public Passenger getPassenger() { return passenger; }
  public String getTitle() { return title; }
  public String getMessage() { return message; }
  public LocalDateTime getSentAt() { return sentAt; }
}