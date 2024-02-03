CREATE TABLE transactions (
    id VARCHAR(36) NOT NULL,
    account_id VARCHAR(36) NOT NULL,
    amount DECIMAL NOT NULL,
    created TIMESTAMP NOT NULL,

    CONSTRAINT pk_transactions PRIMARY KEY (id),
    CONSTRAINT fk_transactions_account_id FOREIGN KEY (account_id) REFERENCES accounts(id)
)
