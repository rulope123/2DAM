DROP DATABASE IF EXISTS pelis;

CREATE DATABASE IF NOT EXISTS pelis;

USE pelis;

CREATE TABLE IF NOT EXISTS peliculas(

    id int PRIMARY KEY,
    título varchar(100),
    imagen varchar(2000),
    valoracion int

);

CREATE TABLE IF NOT EXISTS usuarios(

    usuario varchar(20) PRIMARY KEY,
    contraseña varchar(20)

);

INSERT INTO usuarios (usuario,contraseña) values ('rulope','rulo')

INSERT INTO peliculas (título,imagen,valoracion) values ('una nueva esperanza' , 'https://m.media-amazon.com/images/M/MV5BOTA5NjhiOTAtZWM0ZC00MWNhLThiMzEtZDFkOTk2OTU1ZDJkXkEyXkFqcGdeQXVyMTA4NDI1NTQx._V1_SX300.jpg',8.6); 
INSERT INTO peliculas (título,imagen,valoracion) values ('el imperio contrataca' , 'https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg',8.7);
INSERT INTO peliculas (título,imagen,valoracion) values ('el retorno del jedi' , 'https://m.media-amazon.com/images/M/MV5BOWZlMjFiYzgtMTUzNC00Y2IzLTk1NTMtZmNhMTczNTk0ODk1XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg',8.3);
