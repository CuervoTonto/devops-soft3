package com.aerosmart.flytrack.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "passengers")
public class PassengerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "passport_number", nullable = false, unique = true)
  private String passportNumber;

  @Column(nullable = false)
  private String password;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassportNumber() { return passportNumber; }
  public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}