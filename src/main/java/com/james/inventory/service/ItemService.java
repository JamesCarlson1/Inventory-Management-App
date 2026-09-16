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
        Optional<Account> ifExists = accountDao.findByAccountId(accountId);

        // Can't do "null" since it can never return null. (Since not Optional<Item>) So you have to do ".isPresent()".
        if (!ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Account not found: " + accountId);
        }
        List<Item> existingItems = itemDao.findByAccountId(accountId);

        // This checks to make sure that the item name does not exist already.
        if (existingItems.stream().anyMatch(item -> item.getItemName().equals(itemName))) {
            throw new IllegalArgumentException("ERROR: Item name already exists: " + itemName + "     For given accountId: " + accountId);
        }

        Item newItem = new Item(null, itemName, accountId, amt);
        return itemDao.create(newItem);     
    }

    public void receive (Long itemId, long amt) throws SQLException {

    }

    public Item updateItemName (Long itemId, String newName) throws SQLException {
        Optional<Item> ifExists = itemDao.findByItemId(itemId);

        // Can't do "null" since it can never return null. (Since not Optional<Account>)
        if (!ifExists.isPresent()) {
            throw new IllegalArgumentException("ERROR: Item not found: " + itemId);
        }

        Optional<List<Item>> existingItems = itemDao.findByAccountId(ifExists.getAccountId());

        if () {
            throw new IllegalArgumentException("ERROR: Username already exists: " + newUsername);
        }

        Account updatedAccount = new Account(accountId, newUsername);

        accountDao.update(updatedAccount); // Has to be updated before returned or else it returns as void.
        return updatedAccount;
    }
}
