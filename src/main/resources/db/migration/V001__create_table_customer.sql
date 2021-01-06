CREATE TABLE customer(
    id serial PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email  VARCHAR(50) UNIQUE NOT NULL,
    cpf  VARCHAR(50) UNIQUE NOT NULL,
    data_nascimento TIMESTAMP NOT NULL
);