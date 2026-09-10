package com.james.inventory.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.james.inventory.domain.Account;
import com.james.inventory.domain.Item;

// The JDBC implementation

public class SqliteItemDao implements ItemDao {
    private final Connection connection;

    public SqliteItemDao (Connection connection) {
        this.connection = connection;
    }

    public List<Item> findAll() throws SQLException {
        String sql = "SELECT item_id, account_id, item_name, amt FROM items WHERE account_id = ?";
        List<Item> items = new ArrayList<>();
        
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(rs.getLong("item_id"), rs.getString("item_name")));
                }
                return items;
            }
        }
    }

    public List<Item> findByAccountId(Long accountId) throws SQLException {
    }

    public Optional<Item> findById(Long itemId) throws SQLException {

    }

    public Item create (Item item) throws SQLException {

    }

    public void delete (Long itemId) throws SQLException {

    }
}
