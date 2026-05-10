package com.aerosmart.flytrack.infraestructura.config;

import com.aerosmart.flytrack.aplicacion.servicio.AutenticacionServicio;
import com.aerosmart.flytrack.aplicacion.servicio.EquipajeServicio;
import com.aerosmart.flytrack.aplicacion.servicio.ItinerarioServicio;
import com.aerosmart.flytrack.aplicacion.servicio.NotificacionServicio;
import com.aerosmart.flytrack.aplicacion.servicio.ReservaServicio;
import com.aerosmart.flytrack.aplicacion.servicio.VueloServicio;
import com.aerosmart.flytrack.dominio.puerto.BaggageReportRepository;
import com.aerosmart.flytrack.dominio.puerto.BookingRepository;
import com.aerosmart.flytrack.dominio.puerto.FlightScheduleRepository;
import com.aerosmart.flytrack.dominio.puerto.NotificationRepository;
import com.aerosmart.flytrack.dominio.puerto.PassengerRepository;
import com.aerosmart.flytrack.dominio.puerto.PasswordEncoderPuerto;
import com.aerosmart.flytrack.dominio.puerto.TokenService;
import com.aerosmart.flytrack.dominio.puerto.VueloPuerto;
import com.aerosmart.flytrack.dominio.puerto.ReservaPuerto;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BaggageReportMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.BookingMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.FlightMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.FlightScheduleMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.mapper.PassengerMapper;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.BaggageReportJpaRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.BaggageReportRepositoryAdapter;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.BookingJpaRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.BookingRepositoryAdapter;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.FlightScheduleJpaRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.FlightScheduleRepositoryAdapter;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.NotificationJpaRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.NotificationRepositoryAdapter;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.PassengerJpaRepository;
import com.aerosmart.flytrack.infraestructura.persistencia.repositorio.PassengerRepositoryAdapter;
import com.aerosmart.flytrack.infraestructura.seguridad.JwtTokenServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionServicios {

@Value("${app.jwt.secret: flytrack-secret-key-para-desarrollo-2024}")
private String jwtSecret;

@Bean
public TokenService tokenService() {
    return new JwtTokenServiceAdapter(jwtSecret);
}

@Bean
public PassengerMapper passengerMapper() {
    return new PassengerMapper();
}

@Bean
public FlightMapper flightMapper() {
    return new FlightMapper();
}

@Bean
public FlightScheduleMapper flightScheduleMapper() {
    return new FlightScheduleMapper();
}

@Bean
public BaggageReportMapper baggageReportMapper() {
    return new BaggageReportMapper();
}

@Bean
public BookingMapper bookingMapper(FlightScheduleMapper flightScheduleMapper,
    PassengerMapper passengerMapper) {
    return new BookingMapper(flightScheduleMapper, passengerMapper);
}

@Bean
public PassengerRepository passengerRepository(PassengerJpaRepository jpaRepository,
    PassengerMapper mapper) {
    return new PassengerRepositoryAdapter(jpaRepository, mapper);
}

@Bean
public BookingRepository bookingRepository(BookingJpaRepository jpaRepository,
    BookingMapper mapper, PassengerRepository passengerRepo, FlightScheduleRepository flightScheduleRepo) {
    return new BookingRepositoryAdapter(jpaRepository, mapper, passengerRepo, flightScheduleRepo);
}

@Bean
public FlightScheduleRepository flightScheduleRepository(
    FlightScheduleJpaRepository jpaRepository,
    FlightScheduleMapper mapper) {
    return new FlightScheduleRepositoryAdapter(jpaRepository, mapper);
}

@Bean
public NotificationRepository notificationRepository(NotificationJpaRepository jpaRepository,
    PassengerMapper passengerMapper, PassengerJpaRepository passengerJpaRepository) {
    return new NotificationRepositoryAdapter(jpaRepository, passengerMapper, passengerJpaRepository);
}

@Bean
public BaggageReportRepository baggageReportRepository(
    BaggageReportJpaRepository jpaRepository,
    BaggageReportMapper baggageReportMapper,
    BookingJpaRepository bookingJpaRepository,
    BookingMapper bookingMapper) {
    return new BaggageReportRepositoryAdapter(jpaRepository, baggageReportMapper, bookingJpaRepository, bookingMapper);
}

@Bean
public ItinerarioServicio itinerarioServicio(PassengerRepository passengerRepo,
    BookingRepository bookingRepo) {
    return new ItinerarioServicio(passengerRepo, bookingRepo);
}

@Bean
public EquipajeServicio equipajeServicio(BookingRepository bookingRepo,
    BaggageReportRepository baggageReportRepo, PassengerRepository passengerRepo) {
    return new EquipajeServicio(bookingRepo, baggageReportRepo, passengerRepo);
}

@Bean
public AutenticacionServicio autenticacionServicio(TokenService tokenService,
    PassengerRepository passengerRepo, PasswordEncoderPuerto passwordEncoder) {
    return new AutenticacionServicio(tokenService, passengerRepo, passwordEncoder);
}

@Bean
public NotificacionServicio notificacionServicio(NotificationRepository notificationRepo,
    PassengerRepository passengerRepo) {
    return new NotificacionServicio(notificationRepo, passengerRepo);
}

@Bean
public VueloServicio vueloServicio(FlightScheduleRepository fsRepo) {
    return new VueloServicio((VueloPuerto) fsRepo);
}

@Bean
public ReservaServicio reservaServicio(BookingRepository bRepo,
    PassengerRepository passengerRepo) {
    return new ReservaServicio((ReservaPuerto) bRepo, passengerRepo);
}
}