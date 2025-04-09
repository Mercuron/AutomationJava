CREATE TABLE IF NOT EXISTS books(
id SERIAL PRIMARY KEY,
title VARCHAR(255),
author VARCHAR(255)
);

INSERT INTO books (title,author) VALUES ('Book1','Author1'),('Book2','Author2');