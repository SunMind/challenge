--  liquibase formatted sql
--  changeset mindaugassungaila:2
--  comment: insert demo data
INSERT INTO currency (code, name, symbol) VALUES
    ('USD', 'United States Dollar', '$'),
    ('EUR', 'Euro', '€'),
    ('GBP', 'British Pound', '£');

INSERT INTO bank_account (account_number) VALUES
    ('ACC1234567890'),
    ('ACC9876543210'),
    ('ACC1122334455'),
    ('ACC5566778899'),
    ('ACC4455667788'),
    ('ACC1002003004');

-- Inserting data into account_currency table
INSERT INTO account_currency (account_number, currency_id, balance) VALUES
    ('ACC1234567890', 1, 5000.00),
    ('ACC1234567890', 2, 3000.00),
    ('ACC9876543210', 1, 10000.00),
    ('ACC1122334455', 2, 2000.00),
    ('ACC5566778899', 1, 1500.00),
    ('ACC5566778899', 2, 1000.00),
    ('ACC1002003004', 1, 7500.00);

-- Insert sample data into bank_transaction table
INSERT INTO bank_transaction (account_number, beneficiary_number, operation_date, comment, amount, currency_id)
VALUES
    ('ACC1234567890', 'ACC9876543210', '2024-12-14 10:15:00', 'Payment for services', -500.00, 1),
    ('ACC9876543210', 'ACC1234567890', '2024-12-14 10:15:00', 'Payment for services', 500.00, 1),
    ('ACC9876543210', NULL, '2024-12-14 11:00:00', 'Deposit to account', 1000.00, 1),
    ('ACC1122334455', 'ACC1234567890', '2024-12-13 09:45:00', 'Refund', -150.00, 2),
    ('ACC1234567890', 'ACC1122334455', '2024-12-13 09:45:00', 'Refund', 150.00, 2),
    ('ACC5566778899', 'ACC1234567890', '2024-12-12 14:30:00', 'Payment for goods', -200.00, 2),
    ('ACC1234567890', 'ACC5566778899', '2024-12-12 14:30:00', 'Payment for goods', 200.00, 2),
    ('ACC1002003004', 'ACC1122334455', '2024-12-11 08:00:00', 'Deposit in EUR', -500.00, 2),
    ('ACC1122334455', 'ACC1002003004', '2024-12-11 08:00:00', 'Deposit in EUR', 500.00, 2),
    ('ACC1234567890', 'ACC9876543210', '2024-12-10 16:25:00', 'Payment for invoice', -250.00, 1),
    ('ACC9876543210', 'ACC1234567890', '2024-12-10 16:25:00', 'Payment for invoice', 250.00, 1),
    ('ACC9876543210', NULL, '2024-12-09 13:05:00', 'Deposit from cash', 800.00, 1),
    ('ACC4455667788', 'ACC1234567890', '2024-12-08 10:50:00', 'Payment for services rendered', -600.00, 1),
    ('ACC1234567890', 'ACC4455667788', '2024-12-08 10:50:00', 'Payment for services rendered', 600.00, 1);
