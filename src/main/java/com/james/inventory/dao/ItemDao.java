package com.james.inventory.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.james.inventory.domain.Item;

// The Interface
public interface ItemDao {
    // All of these are contracts that classes that implement this class
    // have to abide by.

    List<Item> findAll() throws SQLException;

    List<Item> findByAccountId(Long accountId) throws SQLException;

    Optional<Item> findById(Long itemId) throws SQLException;

    Item create (Item item) throws SQLException;

    void delete (Long itemId) throws SQLException;
}
