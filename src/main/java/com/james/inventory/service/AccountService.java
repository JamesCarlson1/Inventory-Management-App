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
        return this.accountDao.findAll();
    }

    public Optional<Account> findById (Long accountId) throws SQLException {
        return this.accountDao.findById(accountId);
    }

    public void delete (Long accountId) throws SQLException {
        this.accountDao.delete(accountId);
    }

    public Account createAccount (String username) throws SQLException {
        Optional<Account> ifExists = accountDao.findByUsername(username);

        if (ifExists != null) {
            throw new IllegalArgumentException("ERROR: Username already exists: " + username);
        }
        Account account = new Account(null, username);
        return accountDao.create(account);
    }

    public Account updateUsername(Long accountId, String newUsername) throws SQLException {
        Optional<Account> ifExists = accountDao.findById(accountId);

        if (ifExists == null) {
            throw new IllegalArgumentException("ERROR: Account not found: " + accountId);
        }
        Optional<Account> accWUsername = accountDao.findByUsername(newUsername);

        if (accWUsername != null && accWUsername.get().getAccountId() != accountId) {
            throw new IllegalArgumentException("ERROR: Username already exists: " + newUsername);
        }

        Account updatedAccount = new Account(accountId, newUsername);

        accountDao.update(updatedAccount); // Has to be updated before returned or else it is void.
        return updatedAccount;

    }
}
