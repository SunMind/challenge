--  liquibase formatted sql
--  changeset mindaugassungaila:1
--  comment: creates tables
CREATE TABLE currency (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(3) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    symbol VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS bank_account (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    currency_id INT NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_account_currency FOREIGN KEY (currency_id) REFERENCES currency(id)
);

CREATE TABLE IF NOT EXISTS bank_transaction (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    beneficiary_id BIGINT COMMENT 'Can be null for transactions with no beneficiary',
    operation_date TIMESTAMP NOT NULL,
    comment TEXT,
    amount DECIMAL(15, 2) NOT NULL,
    currency_id INT NOT NULL,
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES bank_account(id),
    CONSTRAINT fk_transaction_beneficiary FOREIGN KEY (beneficiary_id) REFERENCES bank_account(id),
    CONSTRAINT fk_transaction_currency FOREIGN KEY (currency_id) REFERENCES currency(id)
);

-- TODO: add user
-- TODO: add currency_rate