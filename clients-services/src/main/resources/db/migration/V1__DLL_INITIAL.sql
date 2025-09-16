CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE persons
(
    id             VARCHAR(100) PRIMARY KEY DEFAULT gen_random_uuid(),
    name           VARCHAR(100) NOT NULL,
    gender         VARCHAR(20)  NOT NULL,
    age            INT          NOT NULL,
    identification VARCHAR(50)  NOT NULL UNIQUE,
    address        VARCHAR(150) NOT NULL,
    phone          VARCHAR(20)  NOT NULL
);

CREATE TABLE clients
(
    id        VARCHAR(100) PRIMARY KEY,
    client_id VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    status    BOOLEAN      NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_clients_persons FOREIGN KEY (id) REFERENCES persons (id) ON DELETE CASCADE
);
