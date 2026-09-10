package com.james.inventory.dao;

// The Interface

import com.james.inventory.domain.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDao {
    // All of these are contracts that classes that implement this class
    // have to abide by.

    List<Account> findAll() throws SQLException;

    Optional<Account> findById (Long accountId) throws SQLException;

    Optional<Account> findByUsername (String username) throws SQLException;

    Account create (Account account) throws SQLException;

    void delete (Long accountId) throws SQLException;
}