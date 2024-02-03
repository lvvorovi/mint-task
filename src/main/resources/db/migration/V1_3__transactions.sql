CREATE TABLE transactions (
    id VARCHAR(36) NOT NULL,
    account_id_from VARCHAR(36) NOT NULL,
    account_id_to VARCHAR(36) NOT NULL,
    amount_from DECIMAL(12, 2) NOT NULL,
    amount_to DECIMAL(12, 2) NOT NULL,
    created TIMESTAMP NOT NULL,

    CONSTRAINT pk_transactions PRIMARY KEY (id),
    CONSTRAINT fk_transactions_account_id_from FOREIGN KEY (account_id_from) REFERENCES accounts(id),
    CONSTRAINT fk_transactions_account_id_to FOREIGN KEY (account_id_to) REFERENCES accounts(id)
)
