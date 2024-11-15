-- Usar o banco de dados e esquema desejados
SET SCHEMA public;

-- Table: sport
CREATE TABLE IF NOT EXISTS sport (
                                     sport_id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL
);

-- Table: user
CREATE TABLE IF NOT EXISTS users (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      birth_date DATE,
                      email VARCHAR(100) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      phone VARCHAR(20)
);

-- Table: user_sport (associative table for user and sport with skill level)
CREATE TABLE IF NOT EXISTS user_sport (
                            user_id INT NOT NULL,
                            sport_id INT NOT NULL,
                            skill_level VARCHAR(20) CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')),
                            PRIMARY KEY (user_id, sport_id),
                            FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                            FOREIGN KEY (sport_id) REFERENCES sport(sport_id) ON DELETE CASCADE
);

-- Table: event
CREATE TABLE IF NOT EXISTS event (
                       event_id INT AUTO_INCREMENT PRIMARY KEY,
                       event_name VARCHAR(100) NOT NULL,
                       description VARCHAR(255),
                       event_date DATE NOT NULL,
                       event_time TIME NOT NULL,
                       location VARCHAR(255),
                       max_participants INT,
                       skill_level VARCHAR(20) CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')),
                       status VARCHAR(20) CHECK (status IN ('active', 'canceled', 'completed')),
                       organizer_id INT NOT NULL,
                       sport_id INT NOT NULL,
                       FOREIGN KEY (organizer_id) REFERENCES users(user_id) ON DELETE SET NULL,
                       FOREIGN KEY (sport_id) REFERENCES sport(sport_id) ON DELETE SET NULL
);

-- Table: participation (linking users and events with status)
CREATE TABLE IF NOT EXISTS participation (
                               participation_id INT AUTO_INCREMENT PRIMARY KEY,
                               event_id INT NOT NULL,
                               user_id INT NOT NULL,
                               status VARCHAR(20) CHECK (status IN ('confirmed', 'pending', 'canceled')),
                               FOREIGN KEY (event_id) REFERENCES event(event_id) ON DELETE CASCADE,
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Inserir Esportes
INSERT INTO sport (sport_id, name)
VALUES
    (1, 'Basketball'),
    (2, 'Football'),
    (3, 'Tennis');

-- Inserir usuários na tabela 'users'
INSERT INTO users (name, birth_date, email, password, phone) VALUES
                                                                 ('John Doe', '1985-06-15', 'john.doe@example.com', 'password123', '555-0101'),
                                                                 ('Jane Smith', '1990-08-20', 'jane.smith@example.com', 'password456', '555-0102'),
                                                                 ('Alice Brown', '1995-12-10', 'alice.brown@example.com', 'password789', '555-0103'),
                                                                 ('Bob Johnson', '1980-03-25', 'bob.johnson@example.com', 'password321', '555-0104');

-- Inserir dados na tabela 'user_sport' (associativa entre usuários e esportes)
-- Supondo que os esportes já existam na tabela 'sport' com sport_id 1, 2 e 3
INSERT INTO user_sport (user_id, sport_id, skill_level) VALUES
                                                            (1, 1, 'intermediate'),  -- John Doe, Sport 1, Intermediate
                                                            (1, 2, 'beginner'),      -- John Doe, Sport 2, Beginner
                                                            (2, 2, 'advanced'),      -- Jane Smith, Sport 2, Advanced
                                                            (3, 3, 'intermediate'),  -- Alice Brown, Sport 3, Intermediate
                                                            (4, 1, 'beginner');      -- Bob Johnson, Sport 1, Beginner

-- Inserir dados na tabela 'event'
-- Supondo que os usuários e esportes já existam com user_id e sport_id adequados
INSERT INTO event (event_name, description, event_date, event_time, location, max_participants, skill_level, status, organizer_id, sport_id) VALUES
                                                                                                                                                 ('Basketball Tournament', 'A fun basketball event for all levels.', '2024-12-25', '10:00:00', 'Central Park', 20, 'intermediate', 'active', 1, 1),
                                                                                                                                                 ('Soccer Championship', 'A competitive soccer event for advanced players.', '2024-12-26', '14:00:00', 'Stadium Arena', 16, 'advanced', 'active', 2, 2),
                                                                                                                                                 ('Tennis Match', 'A friendly tennis match for beginners.', '2024-12-28', '08:00:00', 'City Courts', 10, 'beginner', 'active', 3, 3);

-- Inserir dados na tabela 'participation'
-- Supondo que usuários e eventos já existam com user_id e event_id adequados
INSERT INTO participation (event_id, user_id, status) VALUES
                                                          (1, 1, 'confirmed'),  -- John Doe participando do evento de Basquete
                                                          (1, 2, 'pending'),    -- Jane Smith aguardando confirmação no evento de Basquete
                                                          (2, 3, 'confirmed'),  -- Alice Brown participando do evento de Futebol
                                                          (3, 4, 'confirmed');  -- Bob Johnson participando do evento de Tênis

