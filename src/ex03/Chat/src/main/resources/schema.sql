DROP SCHEMA iF EXISTS chatSchema CASCADE;

CREATE SCHEMA IF NOT EXISTS chatSchema;

CREATE TABLE IF NOT EXISTS chatSchema.User(
    id serial PRIMARY KEY,
    login text NOT NULL UNIQUE,
    password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chatSchema.Room(
    id serial PRIMARY KEY,
    name text NOT NULL UNIQUE,
    owner bigint  NOT NULL REFERENCES chatSchema.User(id)
);

CREATE TABLE IF NOT EXISTS chatSchema.Message(
    id serial PRIMARY KEY,
    author bigint  NOT NULL REFERENCES chatSchema.User(id),
    room bigint NOT NULL references chatSchema.Room(id),
    text text,
    dateAndTime timestamp DEFAULT CURRENT_TIMESTAMP
);