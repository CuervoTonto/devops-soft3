package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class NotificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "passenger_id", nullable = false)
  private PassengerEntity passenger;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String message;

  @Column(name = "sent_at", nullable = false)
  private LocalDateTime sentAt;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public PassengerEntity getPassenger() { return passenger; }
  public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  public LocalDateTime getSentAt() { return sentAt; }
  public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}