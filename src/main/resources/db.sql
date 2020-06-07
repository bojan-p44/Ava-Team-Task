CREATE DATABASE ava;

CREATE TABLE ava.users (
    id INT NOT NULL PRIMARY KEY auto_increment,
    first_name VARCHAR (50) NOT NULL ,
    last_name VARCHAR (50) NOT NULL ,
    email VARCHAR (50) NOT NULL ,
    password VARCHAR (255) NOT NULL ,
    country VARCHAR (50) NOT NULL ,
    address VARCHAR (255) NOT NULL ,
    is_admin INT NOT NULL DEFAULT 0
);

INSERT INTO ava.users (first_name, last_name, email, password, country, address, is_admin)
VALUES ("Admin", "Admin", "admin@email.com", "admin", "Serbia", "Belgrade, New Belgrade", 1);

INSERT INTO ava.users (first_name, last_name, email, password, country, address, is_admin)
VALUES ("Standard", "Standard", "standard@email.com", "standard", "USA", "NY", 0);
