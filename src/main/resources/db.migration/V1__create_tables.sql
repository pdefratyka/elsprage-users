CREATE TABLE elsprage_words.users
(
    id serial NOT NULL,
    login varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    email varchar(200),
    PRIMARY KEY (id)
);