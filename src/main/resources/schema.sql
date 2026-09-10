CREATE TABLE accounts (
    account_id INTEGER PRIMARY KEY,
    account_name TEXT,
    UNIQUE(account_name)
);

CREATE TABLE items (
    item_id INTEGER PRIMARY KEY,
    account_id INTEGER,
    item_name TEXT,
    amt INTEGER,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE,
    UNIQUE(account_id, item_name),
    CHECK (amt >= 0)
);
INSERT INTO accounts (account_name) VALUES ('testuser2');