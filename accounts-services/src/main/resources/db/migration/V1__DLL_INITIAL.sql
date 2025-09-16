CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE accounts
(
    id             varchar(100) PRIMARY KEY DEFAULT gen_random_uuid(),
    account_number VARCHAR(12)      NOT NULL UNIQUE,
    type           VARCHAR(10)      NOT NULL,
    amount         DOUBLE PRECISION NOT NULL,
    client_id    VARCHAR(50)     NOT NULL,
    status         BOOLEAN          NOT NULL
);

CREATE TABLE accounts_movements
(
    id             varchar(100) PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id     varchar(100)             NOT NULL,
    date           TIMESTAMPTZ      NOT NULL,
    type_movement  VARCHAR(10)      NOT NULL,
    amount         DOUBLE PRECISION NOT NULL,
    balance        DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

