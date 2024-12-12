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
