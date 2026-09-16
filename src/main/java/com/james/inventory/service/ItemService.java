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

    public ItemService (ItemDao itemDao, AccountDao accountDao) {
        this.itemDao = itemDao;
        this.accountDao = accountDao;
    }

    public List<Item> findByAccountId (Long accountId) throws SQLException {
        return this.itemDao.findByAccountId(accountId);
    }

    public Optional<Item> findByItemId (Long itemId) throws SQLException {
        return this.itemDao.findByItemId(itemId);
    }

    public void delete (Long itemId) throws SQLException {
        this.itemDao.delete(itemId);
    }

    public Item createItem (Long accountId, String itemName, long amt) throws SQLException {
        Optional<Account> ifExists = accountDao.findById(accountId);
        if (!ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Could not locate account: " + accountId);
        }
        List<Item> existingItems = itemDao.findByAccountId(accountId);
        boolean nameTaken = ifExists.stream().anyMatch(existingItems -> existingItems.getItemName().equals(itemName));

    }

    public receive (Long itemId, long amt) throws SQLException {

    }

    public updateItemName (Long itemId, String newName) throws SQLException {

    }
}
