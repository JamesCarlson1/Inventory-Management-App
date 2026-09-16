package com.james.inventory.service;

import java.sql.SQLException;

// Files that the method can use and call from.
import java.util.List;
import java.util.Optional;
import com.james.inventory.domain.Account;
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
