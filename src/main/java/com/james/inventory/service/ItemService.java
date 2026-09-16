package com.james.inventory.service;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

// Files that the method can use and call from.
import com.james.inventory.domain.Account;
import com.james.inventory.domain.Item;
import com.james.inventory.dao.AccountDao;
import com.james.inventory.dao.ItemDao;

public class ItemService {
    private final ItemDao itemDao;
    private final AccountDao accountDao;

    public ItemService (ItemDao itemDao, AccountDao acountDao) {
        this.itemDao = itemDao;
        this.accountDao = accountDao;
    }

    public List<Item> findByAccountId (Long accountId) throws SQLException {
        return this.findByAccountId(accountId);
    }

    public List<Item> findByItemId (Long itemId) throws SQLException {
        return this.findByItemId(itemId);
    }

    public void delete (Long itemId) throws SQLException {
        this.itemDao.delete(itemId);
    }

    public createItem (Long accountId, String itemName, long amt) throws SQLException {
        Optional<Item> ifExists = accountDao.findById();
        boolean nameTaken = ifExists.stream().anyMatch(item -> item.getItemName().equals(itemName));

        // Can't do "null" since it can never return null.
        if (ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Username already exists: " + username);
        }
        Account account = new Account(null, username);
        return accountDao.create(account);
    }

    public receive (Long itemId, long amt) throws SQLException {

    }

    public updateItemName (Long itemId, String newName) throws SQLException {

    }
}
