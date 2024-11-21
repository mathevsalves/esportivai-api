-- Usar o banco de dados e esquema desejados
SET SCHEMA public;

-- -- Table: sport
-- CREATE TABLE IF NOT EXISTS sport (
--                                      sport_id INT AUTO_INCREMENT PRIMARY KEY,
--                                      name VARCHAR(100) NOT NULL
-- );
--
-- -- Table: user
-- CREATE TABLE IF NOT EXISTS users (
--                       user_id INT AUTO_INCREMENT PRIMARY KEY,
--                       name VARCHAR(100) NOT NULL,
--                       birth_date DATE,
--                       email VARCHAR(100) UNIQUE NOT NULL,
--                       password VARCHAR(255) NOT NULL,
--                       phone VARCHAR(20)
-- );
--
-- -- Table: user_sport (associative table for user and sport with skill level)
-- CREATE TABLE IF NOT EXISTS user_sport (
--                             user_id INT NOT NULL,
--                             sport_id INT NOT NULL,
--                             skill_level VARCHAR(20) CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')),
--                             PRIMARY KEY (user_id, sport_id),
--                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
--                             FOREIGN KEY (sport_id) REFERENCES sport(sport_id) ON DELETE CASCADE
-- );
--
-- -- Table: event
-- CREATE TABLE IF NOT EXISTS event (
--                        event_id INT AUTO_INCREMENT PRIMARY KEY,
--                        event_name VARCHAR(100) NOT NULL,
--                        description VARCHAR(255),
--                        event_date DATE NOT NULL,
--                        event_time TIME NOT NULL,
--                        location VARCHAR(255),
--                        max_participants INT,
--                        skill_level VARCHAR(20) CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')),
--                        status VARCHAR(20) CHECK (status IN ('active', 'canceled', 'completed')),
--                        organizer_id INT NOT NULL,
--                        sport_id INT NOT NULL,
--                        FOREIGN KEY (organizer_id) REFERENCES users(user_id) ON DELETE SET NULL,
--                        FOREIGN KEY (sport_id) REFERENCES sport(sport_id) ON DELETE SET NULL
-- );
--
-- -- Table: participation (linking users and events with status)
-- CREATE TABLE IF NOT EXISTS participation (
--                                participation_id INT AUTO_INCREMENT PRIMARY KEY,
--                                event_id INT NOT NULL,
--                                user_id INT NOT NULL,
--                                status VARCHAR(20) CHECK (status IN ('confirmed', 'pending', 'canceled')),
--                                FOREIGN KEY (event_id) REFERENCES event(event_id) ON DELETE CASCADE,
--                                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
-- );

-- -- Inserir Esportes
-- INSERT INTO sport (sport_id, name)
-- VALUES
--     (1, 'Basketball'),
--     (2, 'Football'),
--     (3, 'Tennis');

-- Tabela: users
CREATE TABLE IF NOT EXISTS users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     birth_date DATE,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL, -- Deve ser criptografada antes de ser armazenada
                                     phone VARCHAR(20),
                                     skill_level VARCHAR(20) NOT NULL CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')) -- Nível de habilidade
);

-- Tabela: event
CREATE TABLE IF NOT EXISTS event (
                                     event_id INT AUTO_INCREMENT PRIMARY KEY,
                                     event_name VARCHAR(100) NOT NULL,
                                     description VARCHAR(255),
                                     event_date DATE NOT NULL,
                                     event_time TIME NOT NULL,
                                     location VARCHAR(255),
                                     max_participants INT NOT NULL, -- Capacidade máxima
                                     skill_level VARCHAR(20) NOT NULL CHECK (skill_level IN ('beginner', 'intermediate', 'advanced')), -- Nível de habilidade do evento
                                     status VARCHAR(20) DEFAULT 'active' CHECK (status IN ('active', 'canceled', 'completed')), -- Status do evento
                                     organizer_id INT NOT NULL, -- Criador do evento
                                     FOREIGN KEY (organizer_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Tabela: participation
CREATE TABLE IF NOT EXISTS participation (
                                             participation_id INT AUTO_INCREMENT PRIMARY KEY,
                                             event_id INT NOT NULL, -- Evento associado
                                             user_id INT NOT NULL, -- Usuário participante
                                             status VARCHAR(20) NOT NULL CHECK (status IN ('confirmed', 'pending', 'canceled')), -- Status da participação
                                             participation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data de registro da participação
                                             FOREIGN KEY (event_id) REFERENCES event(event_id) ON DELETE CASCADE,
                                             FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Tabela: invite
CREATE TABLE IF NOT EXISTS invite (
                                      invite_id INT AUTO_INCREMENT PRIMARY KEY,
                                      event_id INT NOT NULL, -- Evento relacionado ao convite
                                      inviter_id INT NOT NULL, -- Usuário que enviou o convite (organizador)
                                      invitee_id INT NOT NULL, -- Usuário que foi convidado
                                      status VARCHAR(20) NOT NULL CHECK (status IN ('sent', 'accepted', 'declined')), -- Status do convite
                                      expiration_date DATE NOT NULL, -- Validade do convite
                                      FOREIGN KEY (event_id) REFERENCES event(event_id) ON DELETE CASCADE,
                                      FOREIGN KEY (inviter_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                      FOREIGN KEY (invitee_id) REFERENCES users(user_id) ON DELETE CASCADE,
                                      CHECK (inviter_id <> invitee_id) -- Regra para evitar auto-convites
);

-- Índices para a tabela users
CREATE INDEX idx_users_email ON users(email); -- Índice para busca rápida por e-mail
CREATE INDEX idx_users_skill_level ON users(skill_level); -- Índice para busca por nível de habilidade

-- Índices para a tabela event
CREATE INDEX idx_event_status ON event(status); -- Índice para busca por status de evento
CREATE INDEX idx_event_skill_level ON event(skill_level); -- Índice para busca por nível de habilidade do evento
CREATE INDEX idx_event_date_time ON event(event_date, event_time); -- Índice composto para buscas por data e hora
CREATE INDEX idx_event_organizer_id ON event(organizer_id); -- Índice para buscas rápidas por organizador

-- Índices para a tabela participation
CREATE INDEX idx_participation_event_id ON participation(event_id); -- Índice para busca rápida por evento
CREATE INDEX idx_participation_user_id ON participation(user_id); -- Índice para busca rápida por usuário
CREATE INDEX idx_participation_status ON participation(status); -- Índice para busca por status de participação

-- Índices para a tabela invite
CREATE INDEX idx_invite_event_id ON invite(event_id); -- Índice para busca rápida por evento
CREATE INDEX idx_invite_inviter_id ON invite(inviter_id); -- Índice para busca por quem enviou o convite
CREATE INDEX idx_invite_invitee_id ON invite(invitee_id); -- Índice para busca por quem foi convidado
CREATE INDEX idx_invite_status ON invite(status); -- Índice para busca por status do convite
CREATE INDEX idx_invite_expiration_date ON invite(expiration_date); -- Índice para validar convites expirados

-- Verificação de constraints adicionais
-- Garante unicidade nas participações por usuário e evento
ALTER TABLE participation ADD CONSTRAINT uq_user_event UNIQUE (event_id, user_id);

-- Garante unicidade nos convites enviados para um mesmo evento e usuário
ALTER TABLE invite ADD CONSTRAINT uq_event_invitee UNIQUE (event_id, invitee_id);

-- Proteção adicional para excluir eventos apenas se todas as participações forem removidas
-- ALTER TABLE event ADD CONSTRAINT fk_event_no_orphan FOREIGN KEY (event_id) REFERENCES participation(event_id) ON DELETE RESTRICT;

-- Ajuste na exclusão de usuários (configurado para evitar remoção de organizadores sem validação explícita)
-- ALTER TABLE users ADD CONSTRAINT fk_users_no_orphan FOREIGN KEY (user_id) REFERENCES event(organizer_id) ON DELETE SET NULL;

INSERT INTO users (name, birth_date, email, password, phone, skill_level) VALUES
                                                                              ('Alice Silva', '1995-03-12', 'alice.silva@gmail.com', 'password123', '555-1234', 'beginner'),
                                                                              ('Bruno Souza', '1988-07-23', 'bruno.souza@gmail.com', 'password123', '555-2345', 'intermediate'),
                                                                              ('Carla Dias', '1990-05-11', 'carla.dias@gmail.com', 'password123', '555-3456', 'advanced'),
                                                                              ('Daniel Santos', '1993-09-02', 'daniel.santos@gmail.com', 'password123', '555-4567', 'beginner'),
                                                                              ('Eduarda Nunes', '2000-01-15', 'eduarda.nunes@gmail.com', 'password123', '555-5678', 'intermediate'),
                                                                              ('Fernando Lopes', '1985-11-03', 'fernando.lopes@gmail.com', 'password123', '555-6789', 'advanced'),
                                                                              ('Gabriela Rocha', '1997-08-30', 'gabriela.rocha@gmail.com', 'password123', '555-7890', 'beginner'),
                                                                              ('Henrique Lima', '1992-04-25', 'henrique.lima@gmail.com', 'password123', '555-8901', 'intermediate'),
                                                                              ('Isabela Costa', '1996-12-10', 'isabela.costa@gmail.com', 'password123', '555-9012', 'advanced'),
                                                                              ('João Martins', '1994-06-17', 'joao.martins@gmail.com', 'password123', '555-0123', 'beginner'),
                                                                              ('Karen Mendes', '1999-02-22', 'karen.mendes@gmail.com', 'password123', '555-1124', 'intermediate'),
                                                                              ('Lucas Pereira', '1987-10-08', 'lucas.pereira@gmail.com', 'password123', '555-2234', 'advanced'),
                                                                              ('Mariana Farias', '1991-03-19', 'mariana.farias@gmail.com', 'password123', '555-3345', 'beginner'),
                                                                              ('Nicolas Teixeira', '1998-07-05', 'nicolas.teixeira@gmail.com', 'password123', '555-4456', 'intermediate'),
                                                                              ('Olivia Borges', '1989-05-14', 'olivia.borges@gmail.com', 'password123', '555-5567', 'advanced'),
                                                                              ('Paulo Almeida', '1983-11-21', 'paulo.almeida@gmail.com', 'password123', '555-6678', 'beginner'),
                                                                              ('Quésia Duarte', '1995-04-09', 'quesia.duarte@gmail.com', 'password123', '555-7789', 'intermediate'),
                                                                              ('Roberto Carvalho', '1990-09-27', 'roberto.carvalho@gmail.com', 'password123', '555-8890', 'advanced'),
                                                                              ('Sofia Castro', '1997-01-04', 'sofia.castro@gmail.com', 'password123', '555-9901', 'beginner'),
                                                                              ('Tiago Freitas', '1993-06-11', 'tiago.freitas@gmail.com', 'password123', '555-1012', 'intermediate');

INSERT INTO event (event_name, description, event_date, event_time, location, max_participants, skill_level, status, organizer_id) VALUES
                                                                                                                                                 ('Futebol Amador', 'Jogo amistoso para iniciantes', '2024-12-01', '15:00:00', 'Campo 1', 20, 'beginner', 'active', 1),
                                                                                                                                                 ('Corrida Noturna', 'Evento de corrida para intermediários', '2024-12-05', '20:00:00', 'Pista Central', 50, 'intermediate', 'active', 2),
                                                                                                                                                 ('Basquete Avançado', 'Treino para jogadores experientes', '2024-12-10', '18:00:00', 'Quadra A', 10, 'advanced', 'active', 3),
                                                                                                                                                 ('Aula de Yoga', 'Sessão relaxante para todos os níveis', '2024-11-25', '07:30:00', 'Sala Zen', 15, 'beginner', 'active', 4),
                                                                                                                                                 ('Treino de Tênis', 'Prática individual para intermediários', '2024-12-15', '10:00:00', 'Quadra 5', 4, 'intermediate', 'active', 5),
                                                                                                                                                 ('Maratona Urbana', 'Evento público para corredores', '2024-12-20', '06:00:00', 'Parque Central', 100, 'advanced', 'active', 6),
                                                                                                                                                 ('Vôlei de Praia', 'Partida amistosa na praia', '2024-12-03', '16:00:00', 'Praia Norte', 12, 'beginner', 'active', 7),
                                                                                                                                                 ('Caminhada Ecológica', 'Evento de integração ao ar livre', '2024-12-12', '08:00:00', 'Trilha Verde', 30, 'beginner', 'active', 8),
                                                                                                                                                 ('Treino de Boxe', 'Aulas para lutadores avançados', '2024-12-08', '19:00:00', 'Academia Max', 8, 'advanced', 'active', 9),
                                                                                                                                                 ('Futebol Feminino', 'Jogo exclusivo para mulheres', '2024-12-04', '14:00:00', 'Campo 2', 22, 'intermediate', 'active', 10),
                                                                                                                                                 ('Corrida Infantil', 'Evento para crianças', '2024-12-22', '10:00:00', 'Pista Júnior', 30, 'beginner', 'active', 11),
                                                                                                                                                 ('Treino de Natação', 'Sessão técnica para nadadores avançados', '2024-12-11', '17:30:00', 'Piscina Azul', 10, 'advanced', 'active', 12),
                                                                                                                                                 ('Treino de Artes Marciais', 'Prática avançada de técnicas', '2024-12-18', '19:30:00', 'Dojo Samurai', 12, 'advanced', 'active', 13),
                                                                                                                                                 ('Workshop de Escalada', 'Curso de escalada para iniciantes', '2024-12-19', '09:00:00', 'Paredão Alpha', 15, 'beginner', 'active', 14),
                                                                                                                                                 ('Campeonato de Xadrez', 'Torneio aberto para todos os níveis', '2024-12-09', '10:00:00', 'Clube Rei Branco', 30, 'intermediate', 'active', 15),
                                                                                                                                                 ('Treino Funcional', 'Treinamento físico aberto', '2024-12-06', '07:00:00', 'Parque Fitness', 20, 'beginner', 'active', 16),
                                                                                                                                                 ('Rugby Amador', 'Jogo coletivo amistoso', '2024-12-13', '15:30:00', 'Campo Leste', 20, 'intermediate', 'active', 17),
                                                                                                                                                 ('Jiu-Jitsu Avançado', 'Treino para atletas avançados', '2024-12-16', '18:00:00', 'Academia Fighter', 15, 'advanced', 'active', 18),
                                                                                                                                                 ('Futebol Infantil', 'Partida para crianças', '2024-12-20', '16:00:00', 'Campo Júnior', 22, 'beginner', 'active', 19),
                                                                                                                                                 ('Dança Zumba', 'Aula coletiva para iniciantes', '2024-12-07', '18:30:00', 'Studio Dance', 25, 'beginner', 'active', 20);

INSERT INTO participation (event_id, user_id, status) VALUES
                                                          (1, 1, 'confirmed'),
                                                          (1, 2, 'pending'),
                                                          (2, 3, 'confirmed'),
                                                          (2, 4, 'confirmed'),
                                                          (3, 5, 'pending'),
                                                          (3, 6, 'confirmed'),
                                                          (4, 7, 'canceled'),
                                                          (4, 8, 'confirmed'),
                                                          (5, 9, 'pending'),
                                                          (5, 10, 'confirmed'),
                                                          (6, 11, 'confirmed'),
                                                          (6, 12, 'pending'),
                                                          (7, 13, 'confirmed'),
                                                          (7, 14, 'confirmed'),
                                                          (8, 15, 'pending'),
                                                          (8, 16, 'confirmed'),
                                                          (9, 17, 'canceled'),
                                                          (9, 18, 'confirmed'),
                                                          (10, 19, 'pending'),
                                                          (10, 20, 'confirmed');

-- INSERT INTO invite (event_id, inviter_id, invitee_id, status, expiration_date) VALUES
--                                                                                    (1, 1, 2, 'sent', '2024-11-30'),
--                                                                                    (2, 2, 3, 'accepted', '2024-12-04'),
--                                                                                    (3, 3, 4, 'refused', '2024-12-09'),
--                                                                                    (4, 4, 5, 'accepted', '2024-11-24'),
--                                                                                    (5, 5, 6, 'sent', '2024-12-14'),
--                                                                                    (6, 6, 7, 'accepted', '2024-12-19'),
--                                                                                    (7, 7, 8, 'refused', '2024-12-02'),
--                                                                                    (8, 8, 9, 'sent', '2024-12-11'),
--                                                                                    (9, 9, 10, 'accepted', '2024-12-07'),
--                                                                                    (10, 10, 11, 'sent', '2024-12-03'),
--                                                                                    (11, 11, 12, 'accepted', '2024-12-21'),
--                                                                                    (12, 12, 13, 'refused', '2024-12-10'),
--                                                                                    (13, 13, 14, 'sent', '2024-12-17'),
--                                                                                    (14, 14, 15, 'accepted', '2024-12-18'),
--                                                                                    (15, 15, 16, 'sent', '2024-12-08'),
--                                                                                    (16, 16, 17, 'accepted', '2024-12-05'),
--                                                                                    (17, 17, 18, 'sent', '2024-12-12'),
--                                                                                    (18, 18, 19, 'refused', '2024-12-15'),
--                                                                                    (19, 19, 20, 'accepted', '2024-12-19'),
--                                                                                    (20, 20, 1, 'sent', '2024-12-06');
