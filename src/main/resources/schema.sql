-- Usar o banco de dados e esquema desejados
SET SCHEMA public;

-- -- Create table User
CREATE TABLE Users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100)        NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100)        NOT NULL
);

-- Create table Sport
CREATE TABLE Sport
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Create table SportUser (many-to-many relationship between User and Sport)
CREATE TABLE SportUser
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT      NOT NULL,
    sport_id    BIGINT      NOT NULL,
    skill_level VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (sport_id) REFERENCES Sport (id) ON DELETE CASCADE,
    UNIQUE (user_id, sport_id)
);

-- Create table Event
CREATE TABLE Event
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    description      VARCHAR(255),
    sport_id         BIGINT       NOT NULL,
    date             DATE         NOT NULL,
    time             TIME         NOT NULL,
    location         VARCHAR(100) NOT NULL,
    max_participants INT          NOT NULL,
    skill_level      VARCHAR(50)  NOT NULL,
    organizer_id     BIGINT       NOT NULL,
    FOREIGN KEY (sport_id) REFERENCES Sport (id),
    FOREIGN KEY (organizer_id) REFERENCES Users (id)
);

-- Create table Participation
CREATE TABLE Participant
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Event (id) ON DELETE CASCADE,
    UNIQUE (user_id, event_id) -- Prevent duplicate participation
);

-- Indexes
CREATE INDEX idx_event_sport ON Event (sport_id);
CREATE INDEX idx_event_organizer ON Event (organizer_id);
CREATE INDEX idx_participant_user_event ON Participant (user_id, event_id);

-- Insert sample data for User
INSERT INTO Users (name, email, password)
VALUES ('John Doe', 'john.doe@example.com', 'password123'),
       ('Jane Smith', 'jane.smith@example.com', 'password456');

-- Insert sample data for Sport
INSERT INTO Sport (name)
VALUES ('Futebol'),
       ('Basquete'),
       ('Vôlei'),
       ('Tênis'),
       ('Natação');

-- Insert sample data for SportUser
INSERT INTO SportUser (user_id, sport_id, skill_level)
VALUES (1, 1, 'Intermediario'),
       (1, 2, 'Iniciante'),
       (2, 3, 'Avançado');

-- Insert sample data for Event
INSERT INTO Event (name, description, sport_id, date, time, location, max_participants, skill_level, organizer_id)
VALUES ('Sunday Soccer Match', 'Friendly soccer game at the park', 1, '2024-12-10', '15:00:00', 'Central Park', 10,
        'Intermediate', 1),
       ('Tennis Tournament', 'Competitive tennis match', 3, '2024-12-15', '10:00:00', 'City Tennis Courts', 8,
        'Advanced', 2);

-- Insert sample data for Participation
INSERT INTO Participant (user_id, event_id)
VALUES (1, 1),
       (2, 2);
