package com.james.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import com.james.inventory.dao.AccountDao;
import com.james.inventory.dao.ItemDao;
import com.james.inventory.dao.SqliteAccountDao;
import com.james.inventory.dao.SqliteItemDao;
import com.james.inventory.service.AccountService;
import com.james.inventory.service.ItemService;

@Configuration
public class AppConfig {
    @Bean
    public Connection connection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory.db");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    @Bean
    public AccountDao accountDao (Connection connection) {
        return new SqliteAccountDao(connection);
    }

    @Bean 
    public ItemDao itemDao (Connection connection) {
        return new SqliteItemDao(connection);
    }

    @Bean 
    public AccountService accountService (AccountDao accountDao) {
        return new AccountService(accountDao);
    }

    @Bean
    public ItemService itemService (ItemDao itemDao, AccountDao accountDao) {
        return new ItemService(itemDao, accountDao);
    }
}
