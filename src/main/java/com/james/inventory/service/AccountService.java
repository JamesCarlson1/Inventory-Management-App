package com.james.inventory.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

// Files that the method can use and call from.
import com.james.inventory.dao.AccountDao;

public class AccountService {
    private final AccountDao accountDao;

    public AccountService (AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAll() throws SQLException {

    }

    public Optional<Account> findById (Long accountId) throws SQLException {

    }
    public void delete (Long accountId) throws SQLException {

    }

    public Account createAccount (String username) throws SQLException {

    }

    public void updateUsername(Long accountId, String newUsername) throws SQLException {

    }
}
