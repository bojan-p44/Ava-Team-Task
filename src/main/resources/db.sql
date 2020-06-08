CREATE DATABASE ava_db;

CREATE TABLE ava_db.users (
    id INT NOT NULL PRIMARY KEY auto_increment,
    first_name VARCHAR (50) NOT NULL ,
    last_name VARCHAR (50) NOT NULL ,
    email VARCHAR (50) NOT NULL ,
    password VARCHAR (255) NOT NULL ,
    country VARCHAR (50) NOT NULL ,
    address VARCHAR (255) NOT NULL ,
    role VARCHAR (20) NOT NULL
);

INSERT INTO ava_db.users (first_name, last_name, email, password, country, address, role)
VALUES ("Admin", "Admin", "admin@email.com", "$2a$10$/dC8AiRx7LXbHkvFQ4O2zuDdGcWAzXGOB4fdSm8HdjX5G2wrsJJ2m", "Serbia", "Belgrade, New Belgrade", "ROLE_ADMIN");

INSERT INTO ava_db.users (first_name, last_name, email, password, country, address, role)
VALUES ("Standard", "Standard", "standard@email.com", "$2a$10$jYNd9gGRO9fIAneI/BVB8exONWQbhx/leU6sE6cSFDihQ0nczmBK.", "USA", "NY", "ROLE_STANDARD");
