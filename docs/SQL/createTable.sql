use bowling_game;

-- Crear la tabla bowling_games
CREATE TABLE bowling_games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    rolls JSON
);

-- Insertar filas en la tabla bowling_games
INSERT INTO bowling_games (name, rolls)
VALUES ('Roberto', '[1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6]'),
       ('Pedro', '[10, 9, 1, 5, 5, 7, 2, 10, 10 ,10, 9, 0, 8, 2, 9, 1, 10]');
