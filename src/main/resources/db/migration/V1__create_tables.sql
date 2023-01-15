CREATE TABLE elsprage_users.users
(
    id serial NOT NULL,
    login varchar(200) NOT NULL,
    password varchar(300) NOT NULL,
    email varchar(200) NOT NULL,
    created_at timestamp NOT NULL,
    role varchar(200),
    PRIMARY KEY (id)
);

ALTER TABLE elsprage_users.users
ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE elsprage_users.users
ADD CONSTRAINT unique_login UNIQUE (login);