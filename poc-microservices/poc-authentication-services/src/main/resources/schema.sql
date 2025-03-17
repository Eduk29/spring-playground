CREATE database poc_authentication_db;

DROP USER IF EXISTS 'dev_poc'@'172.17.0.1';
CREATE USER 'dev_poc'@'172.17.0.1' IDENTIFIED BY 'w83y4LX6OiUB';

GRANT ALL PRIVILEGES ON poc_authentication_db.* TO 'dev_poc'@'172.17.0.1';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS poc_authentication_db.users
(
    id INT(9) auto_increment PRIMARY KEY NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at  VARCHAR(255) NOT NULL,
    updated_at VARCHAR(255),
    person_id INT(9) UNIQUE NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS poc_authentication_db.roles
(
    id INT(9) auto_increment PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_at  VARCHAR(255) NOT NULL,
    updated_at VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS poc_authentication_db.rel_user_roles (
    user_id INT(9) NOT NULL,
    role_id INT(9) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    foreign key (user_id)
    	references poc_authentication_db.users(id),
    foreign key (role_id)
    	references poc_authentication_db.roles(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;