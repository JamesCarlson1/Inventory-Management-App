package com.james.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
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
        String sql = "SELECT account_id, username FROM accounts";
        List<Account> accounts = new ArrayList<>();

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                // Finds and all accounts currently available.
                while (rs.next()) {
                    accounts.add(new Account(rs.getLong("account_id"), rs.getString("username")));
                }
                return accounts;
            }
        }
    }

    public Optional<Account> findById (Long accountId) throws SQLException {
        String sql = "SELECT account_id, username FROM accounts WHERE account_id = ?";

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // This finds the correct user based on the given id.
            pstmt.setLong(1, accountId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getLong("account_id"), rs.getString("username"));
                    return Optional.of(account);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<Account> findByUsername (String username) throws SQLException {

    }

    public Account create (Account account) throws SQLException {

    }

    public void delete (Long accountId) throws SQLException {

    }
}
