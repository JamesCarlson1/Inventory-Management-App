package com.james.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sql = "SELECT account_id, account_name FROM accounts";
        List<Account> accounts = new ArrayList<>();

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                // Finds and all accounts currently available.
                while (rs.next()) {
                    accounts.add(new Account(rs.getLong("account_id"), rs.getString("account_name")));
                }
                return accounts;
            }
        }
    }

    public Optional<Account> findById (Long userId) throws SQLException {
        String sql = "SELECT account_id, account_name FROM accounts WHERE account_id = ?";

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // This finds the correct account_name based on the given accountId.
            pstmt.setLong(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getLong("account_id"), rs.getString("account_name"));
                    return Optional.of(account);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<Account> findByUsername (String username) throws SQLException {
        String sql = "SELECT account_name, account_id FROM accounts WHERE account_name = ?";

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // This finds the correct account_id based on the given username(account_name).
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(rs.getLong("account_id"), rs.getString("account_name"));
                    return Optional.of(account);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Account create (Account account) throws SQLException {
        String sql = "INSERT INTO accounts (account_name) VALUES (?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, account.getUsername());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return new Account(rs.getLong(1), account.getUsername());
                }
            }
        }
        throw new SQLException("ERROR: Creating account failed: no ID obtained");
    }

    public void delete (Long accountId) throws SQLException {

    }
}
