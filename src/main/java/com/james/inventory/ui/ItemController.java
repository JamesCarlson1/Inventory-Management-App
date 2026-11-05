package com.james.inventory.ui;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.sql.SQLException;

import com.james.inventory.domain.Item;
import com.james.inventory.service.ItemService;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController (ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/accounts/{accountId}/items") public List<Item> getItems(@PathVariable Long accountId) throws SQLException {
        return itemService.findByAccountId(accountId);
    }
}
