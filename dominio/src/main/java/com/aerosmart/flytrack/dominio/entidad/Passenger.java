package com.aerosmart.flytrack.dominio.entidad;

import com.aerosmart.flytrack.dominio.valueobject.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Passenger {
private UUID id;
private String firstName;
private String lastName;
private Email email;
private String passportNumber;
private String password;
private List<Booking> bookings = new ArrayList<>();

public Passenger(String firstName, String lastName, Email email, String passportNumber, String password) {
    // this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.passportNumber = passportNumber;
    this.password = password;
}

public Passenger(UUID id, String firstName, String lastName, Email email, String passportNumber, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.passportNumber = passportNumber;
    this.password = password;
}

public void agregarReserva(Booking booking) {
    this.bookings.add(booking);
}

public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }
public String getFirstName() { return firstName; }
public String getLastName() { return lastName; }
public Email getEmail() { return email; }
public String getPassportNumber() { return passportNumber; }
public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }
public List<Booking> getBookings() { return bookings; }
}