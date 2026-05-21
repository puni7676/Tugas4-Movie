CREATE DATABASE IF NOT EXISTS movie_db;
USE movie_db;

CREATE TABLE IF NOT EXISTS movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(100) NOT NULL,
    alur DOUBLE NOT NULL,
    penokohan DOUBLE NOT NULL,
    akting DOUBLE NOT NULL,
    nilai DOUBLE NOT NULL
);

INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES
('Godzilla', 5.0, 4.0, 4.0, (5.0 + 4.0 + 4.0) / 3),
('Jurassic', 3.0, 4.0, 5.0, (3.0 + 4.0 + 5.0) / 3),
('Minions', 3.0, 4.0, 4.0, (3.0 + 4.0 + 4.0) / 3),
('Top Gun Maverick', 4.0, 4.0, 4.0, (4.0 + 4.0 + 4.0) / 3);
