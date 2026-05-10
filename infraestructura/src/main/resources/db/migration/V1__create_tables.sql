-- Extension para generar UUIDs si no los manejas desde la aplicación
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

---
--- 1. MODULO DE VUELOS (Core Domain)
---
CREATE TABLE flights (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    flight_number VARCHAR(10) UNIQUE NOT NULL,
    airline_name VARCHAR(100) NOT NULL,
    aircraft_type VARCHAR(50)
);

CREATE TABLE flight_schedules (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    flight_id UUID NOT NULL REFERENCES flights(id) ON DELETE CASCADE,
    origin_iata CHAR(3) NOT NULL,      -- Ej: 'BOG'
    destination_iata CHAR(3) NOT NULL, -- Ej: 'JFK'
    departure_time TIMESTAMP WITH TIME ZONE NOT NULL,
    arrival_time TIMESTAMP WITH TIME ZONE NOT NULL,
    current_gate VARCHAR(10),
    status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED', -- SCHEDULED, ON_TIME, DELAYED, BOARDING, DEPARTED, LANDED, CANCELLED
    
    CONSTRAINT chk_status CHECK (status IN ('SCHEDULED', 'ON_TIME', 'DELAYED', 'BOARDING', 'DEPARTED', 'LANDED', 'CANCELLED'))
);

---
--- 2. MODULO DE PASAJEROS Y RESERVAS
---
CREATE TABLE passengers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    passport_number VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE bookings (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    passenger_id UUID NOT NULL REFERENCES passengers(id),
    flight_schedule_id UUID NOT NULL REFERENCES flight_schedules(id),
    seat_assignment VARCHAR(5),
    booking_reference CHAR(6) UNIQUE NOT NULL, -- Ej: 'AKZ99L'
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

---
--- 3. MODULO DE EQUIPAJE E INCIDENCIAS (Screaming Infrastructure)
---
CREATE TABLE baggage_reports (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    booking_id UUID NOT NULL REFERENCES bookings(id),
    incident_type VARCHAR(20) NOT NULL, -- DELAYED, LOST, DAMAGED
    description TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN', -- OPEN, IN_PROGRESS, RESOLVED, CLOSED
    reported_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_incident CHECK (incident_type IN ('DELAYED', 'LOST', 'DAMAGED')),
    CONSTRAINT chk_report_status CHECK (status IN ('OPEN', 'IN_PROGRESS', 'RESOLVED', 'CLOSED'))
);

---
--- 4. MODULO DE NOTIFICACIONES (Audit Log para DevOps)
---
CREATE TABLE notifications (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    passenger_id UUID NOT NULL REFERENCES passengers(id),
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    sent_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

---
--- INDEXACIÓN PARA PERFORMANCE
---
CREATE INDEX idx_flight_schedules_departure ON flight_schedules(departure_time);
CREATE INDEX idx_bookings_passenger ON bookings(passenger_id);
CREATE INDEX idx_baggage_reports_booking ON baggage_reports(booking_id);