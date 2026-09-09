package com.james.inventory.domain;

// Initializes an account Java Object.

public class Account {
    private final Long accountId;
    private final String username;

    public Account (Long accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public String getUsername() {
        return username;
    }
}
