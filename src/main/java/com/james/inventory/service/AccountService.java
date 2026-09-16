package com.james.inventory.service;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

// Files that the method can use and call from.
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

    public Optional<Account> findByAccountId (Long accountId) throws SQLException {
        return this.accountDao.findByAccountId(accountId);
    }

    public void delete (Long accountId) throws SQLException {
        this.accountDao.delete(accountId);
    }



    public Account createAccount (String username) throws SQLException {
        Optional<Account> ifExists = accountDao.findByUsername(username);

        // Can't do "null" since it can never return null.
        if (ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Username already exists: " + username);
        }
        Account newAccount = new Account(null, username);
        return accountDao.create(newAccount);
    }

    public Account updateUsername(Long accountId, String newUsername) throws SQLException {
        Optional<Account> ifExists = accountDao.findByAccountId(accountId);

        // Can't do "null" since it can never return null. (Since not Optional<Account>)
        if (!ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Account not found: " + accountId);
        }
        Optional<Account> accWUsername = accountDao.findByUsername(newUsername);

        // This checks to make sure that the account name does not already exist.
        if (accWUsername.isPresent() && !accWUsername.get().getAccountId().equals(accountId)) {
            throw new IllegalArgumentException("ERROR: Username already exists: " + newUsername);
        }

        // Makes new updatedAccount.
        Account updatedAccount = new Account(accountId, newUsername);

        accountDao.update(updatedAccount); // Has to be updated before returned or else it returns as void.
        return updatedAccount;
    }
}
