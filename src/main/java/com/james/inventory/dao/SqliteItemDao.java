package com.james.inventory.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// Files that the method can use and call from.
import com.james.inventory.domain.Item;

// The JDBC implementation

public class SqliteItemDao implements ItemDao {
    private final Connection connection;

    public SqliteItemDao (Connection connection) {
        this.connection = connection;
    }



    // Isn't Optional<Item> since it holds a list and most likely the account already exists.
    public List<Item> findAll() throws SQLException {
        String sql = "SELECT item_id, account_id, item_name, amt FROM items";
        List<Item> items = new ArrayList<>();
        
        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(rs.getLong("item_id"), rs.getString("item_name"), rs.getLong("account_id"), rs.getLong("amt")));
                }
                return items;
            }
        }
    }

    // Isn't Optional<Item> since it holds a list and most likely the account already exists.
    public List<Item> findByAccountId(Long accountId) throws SQLException {
        String sql = "SELECT item_id, item_name, account_id, amt FROM items WHERE account_id = ?";
        List<Item> items = new ArrayList<>();
        
        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // Binds the parameter
            pstmt.setLong(1, accountId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Item(rs.getLong("item_id"), rs.getString("item_name"), rs.getLong("account_id"), rs.getLong("amt")));
                }
                return items;
            }
        }
    }

    public Optional<Item> findByItemId(Long itemId) throws SQLException {
        String sql = "SELECT item_id, account_id, item_name, amt FROM items WHERE item_id = ?";

        // This sends the SQL template to the database so it can be parsed, compiled, and optimized ahead of time.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // Binds the parameter
            pstmt.setLong(1, itemId);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Checks if a row was found.
                if (rs.next()) {
                    Item item = new Item(rs.getLong("item_id"), rs.getString("item_name"), rs.getLong("account_id"), rs.getLong("amt"));
                    return Optional.of(item);
                } else {
                    return Optional.empty();
                }
            }
        }
    }



    public Item create (Item item) throws SQLException {
        String sql = "INSERT INTO items (item_name, account_id, amt) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Binds the parameters.
            pstmt.setString(1, item.getItemName());
            pstmt.setLong(2, item.getAccountId());
            pstmt.setLong(3, item.getAmt());

            // Executes the command.
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return new Item(rs.getLong(1), item.getItemName(), item.getAccountId(), item.getAmt());
                }
            }
        }
        throw new SQLException("ERROR: Creating item failed: no ID obtained");
    }

    public void delete (Long itemId) throws SQLException {
        String sql = "DELETE FROM items WHERE item_id = ?";

        // Try-with-resources manages the PreparedStatement.
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            // Binds the parameter.
            pstmt.setLong(1, itemId);
            // Executes the command.
            pstmt.executeUpdate();
        }
    }

    

    public void update (Item item) throws SQLException {
        String sql = "UPDATE items SET item_name = ?, amt = ? WHERE item_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemName());
            pstmt.setLong(2, item.getAmt());
            pstmt.setLong(3, item.getItemId());

            pstmt.executeUpdate();
        }
    }
}
