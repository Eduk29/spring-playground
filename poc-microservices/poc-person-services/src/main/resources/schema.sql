CREATE database poc_person_db;

DROP USER IF EXISTS 'dev_poc'@'172.17.0.1';
CREATE USER 'dev_poc'@'172.17.0.1' IDENTIFIED BY 'w83y4LX6OiUB';

GRANT ALL PRIVILEGES ON poc_person_db.* TO 'dev_poc'@'172.17.0.1';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS poc_person_db.persons
(
    id INT(9) auto_increment PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    age INT(9),
    cpf VARCHAR(11) UNIQUE NOT NULL,
    user_id INT(9) UNIQUE NOT NULL,
    created_at  VARCHAR(255),
    updated_at VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;