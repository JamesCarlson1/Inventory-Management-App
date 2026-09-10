package com.james.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.james.inventory.domain.Account;

// The JDBC Implementation

public class SqliteAccountDao implements AccountDao {
    // Makes the Database Connection variable.
    private final Connection connection;

    public SqliteAccountDao (Connection connection) {
        this.connection = connection;
    }
    
    public List<Account> findAll() throws SQLException {

    }

    public Optional<Account> findById (Long accountId) throws SQLException {

    }

    public Optional<Account> findByUsername (String username) throws SQLException {

    }

    public Account create (Account account) throws SQLException {

    }

    public void delete (Long accountId) throws SQLException {

    }
}
