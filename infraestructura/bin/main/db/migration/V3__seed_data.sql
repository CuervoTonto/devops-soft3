-- Seed data for testing
INSERT INTO flights (id, flight_number, airline_name, aircraft_type) 
VALUES ('11111111-1111-1111-1111-111111111111', 'AA1234', 'American Airlines', 'Boeing 737');

INSERT INTO flight_schedules (id, flight_id, origin_iata, destination_iata, departure_time, arrival_time, current_gate, status)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    '11111111-1111-1111-1111-111111111111',
    'BOG',
    'JFK',
    '2026-06-15 10:00:00+00',
    '2026-06-15 16:30:00+00',
    'A12',
    'SCHEDULED'
);

-- Test passenger with password: some (hash: $2a$10$E1osu1SAR3yeRmqJOslEUulMW.XhGkMXTJocns4c9U5kfJKWBoXJK)
INSERT INTO passengers (id, first_name, last_name, email, passport_number, password)
VALUES (
    '33333333-3333-3333-3333-333333333333',
    'Test',
    'User',
    'test@example.com',
    'AB1234567',
    '$2a$10$E1osu1SAR3yeRmqJOslEUulMW.XhGkMXTJocns4c9U5kfJKWBoXJK'
);

-- Test booking for test user
INSERT INTO bookings (id, passenger_id, flight_schedule_id, seat_assignment, booking_reference, created_at)
VALUES (
    '44444444-4444-4444-4444-444444444444',
    '33333333-3333-3333-3333-333333333333',
    '22222222-2222-2222-2222-222222222222',
    '12A',
    'TEST01',
    NOW()
);