package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class BookingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "passenger_id", nullable = false)
  private PassengerEntity passenger;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_schedule_id", nullable = false)
  private FlightScheduleEntity flightSchedule;

  @Column(name = "seat_assignment")
  private String seatAssignment;

  @Column(name = "booking_reference", nullable = false, unique = true)
  private String bookingReference;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public PassengerEntity getPassenger() { return passenger; }
  public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }
  public FlightScheduleEntity getFlightSchedule() { return flightSchedule; }
  public void setFlightSchedule(FlightScheduleEntity flightSchedule) { this.flightSchedule = flightSchedule; }
  public String getSeatAssignment() { return seatAssignment; }
  public void setSeatAssignment(String seatAssignment) { this.seatAssignment = seatAssignment; }
  public String getBookingReference() { return bookingReference; }
  public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}