-- Demo users (password_hash is not a real hash: nobody can log in with these accounts yet)
INSERT INTO users (name, email, password_hash, phone_number, role) VALUES
                                                                       ('Dr. Ahmed Ben Ali',  'ahmed@demo.tn',   'CHANGE_ME', '20000001', 'DOCTOR'),
                                                                       ('Dr. Sarra Trabelsi', 'sarra@demo.tn',   'CHANGE_ME', '20000002', 'DOCTOR'),
                                                                       ('Yasmine Gharbi',     'yasmine@demo.tn', 'CHANGE_ME', '20000003', 'PATIENT'),
                                                                       ('Mehdi Jlassi',       'mehdi@demo.tn',   'CHANGE_ME', '20000004', 'PATIENT')
    ON CONFLICT (email) DO NOTHING;

-- Doctor profiles
INSERT INTO doctor_profile (user_id, specialty_id, biography, license_number, years_of_experience, clinic_address)
SELECT u.id, s.id, 'Demo doctor profile.', 'DEMO-001', 8, 'Monastir'
FROM users u, specialty s
WHERE u.email = 'ahmed@demo.tn' AND s.name = 'Cardiology'
    ON CONFLICT (license_number) DO NOTHING;

INSERT INTO doctor_profile (user_id, specialty_id, biography, license_number, years_of_experience, clinic_address)
SELECT u.id, s.id, 'Demo doctor profile.', 'DEMO-002', 5, 'Sousse'
FROM users u, specialty s
WHERE u.email = 'sarra@demo.tn' AND s.name = 'Dermatology'
    ON CONFLICT (license_number) DO NOTHING;

-- 30-minute slots from 09:00 to 11:30, for the next 3 days
INSERT INTO availability_slot (doctor_id, start_at, end_at)
SELECT d.id,
       (CURRENT_DATE + days.day_offset) + TIME '09:00' + slots.n * INTERVAL '30 minutes',
    (CURRENT_DATE + days.day_offset) + TIME '09:30' + slots.n * INTERVAL '30 minutes'
FROM doctor_profile d
    CROSS JOIN generate_series(1, 3) AS days(day_offset)
    CROSS JOIN generate_series(0, 5) AS slots(n)
WHERE d.license_number IN ('DEMO-001', 'DEMO-002')
ON CONFLICT (doctor_id, start_at) DO NOTHING;