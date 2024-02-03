CREATE TABLE accounts (
    id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    currency VARCHAR(10) NOT NULL,

    CONSTRAINT pk_accounts PRIMARY KEY (id),
    CONSTRAINT fk_accounts_users_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT uc_accounts_users_currency UNIQUE (user_id, currency)
)
