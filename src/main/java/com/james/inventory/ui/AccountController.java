package com.james.inventory.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.sql.SQLException;

import com.james.inventory.domain.Account;
import com.james.inventory.service.AccountService;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts") public List<Account> getAccounts() throws SQLException {
        return accountService.findAll();
    }

    @PostMapping("/accounts") public Account createAccount(@RequestParam String username) throws SQLException {
        return accountService.createAccount(username);
    }
}