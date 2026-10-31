CREATE TABLE specialty (
                           id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name          VARCHAR(255),
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       phone_number  VARCHAR(255),
                       role          VARCHAR(255) NOT NULL
);

CREATE TABLE doctor_profile (
                                id                  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                user_id             INTEGER       NOT NULL UNIQUE REFERENCES users (id),
                                specialty_id        INTEGER       NOT NULL REFERENCES specialty (id),
                                biography           VARCHAR(2000),
                                license_number      VARCHAR(255)  NOT NULL UNIQUE,
                                years_of_experience INTEGER,
                                clinic_address      VARCHAR(255)
);

CREATE TABLE availability_slot (
                                   id        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                   doctor_id INTEGER   NOT NULL REFERENCES doctor_profile (id),
                                   start_at  TIMESTAMP NOT NULL,
                                   end_at    TIMESTAMP NOT NULL,
                                   CONSTRAINT uq_slot_doctor_start UNIQUE (doctor_id, start_at),
                                   CONSTRAINT ck_slot_times CHECK (end_at > start_at)
);

CREATE TABLE appointment (
                             id                  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             patient_id          INTEGER      NOT NULL REFERENCES users (id),
                             slot_id             INTEGER      NOT NULL REFERENCES availability_slot (id),
                             status              VARCHAR(255) NOT NULL,
                             created_at          TIMESTAMP    NOT NULL,
                             cancellation_reason VARCHAR(255)
);

-- No double booking: only one active appointment per slot
CREATE UNIQUE INDEX uq_appointment_active_slot
    ON appointment (slot_id)
    WHERE status <> 'CANCELLED';

-- Specialties the app always needs
INSERT INTO specialty (name) VALUES
                                 ('Cardiology'),
                                 ('Dermatology'),
                                 ('Pediatrics'),
                                 ('General Medicine'),
                                 ('Gynecology'),
                                 ('Ophthalmology'),
                                 ('Dentistry');