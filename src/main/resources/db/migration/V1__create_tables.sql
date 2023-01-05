CREATE TABLE elsprage_users.users
(
    id serial NOT NULL,
    login varchar(200) NOT NULL,
    password bytea NOT NULL,
    email varchar(200) NOT NULL,
    createdAt timestamptz NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE elsprage_users.users
ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE elsprage_users.users
ADD CONSTRAINT unique_login UNIQUE (login);