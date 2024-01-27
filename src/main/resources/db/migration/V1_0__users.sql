CREATE TABLE users (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(10) NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_name UNIQUE (name)
)
