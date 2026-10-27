Create Table users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)