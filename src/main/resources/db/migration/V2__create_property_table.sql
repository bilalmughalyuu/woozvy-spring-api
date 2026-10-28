CREATE TABLE property (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    category VARCHAR(255),
    price DOUBLE,
    user_id BIGINT NOT NULL,

    CONSTRAINT `PRIMARY` PRIMARY KEY (id)

    CONSTRAINT fk_property_user
       FOREIGN KEY (user_id)
       REFERENCES users(id)
)