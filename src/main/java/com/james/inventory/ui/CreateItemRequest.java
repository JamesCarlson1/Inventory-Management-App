package com.james.inventory.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.james.inventory.domain.Item;
import com.james.inventory.service.ItemService;

@RestController 
public class CreateItemRequest {
    public record CreateItemRequest (Long accountId, String itemName, long cost, long amt) {

    }

    @PostMapping("/accounts/{accountId}/items") public Item createItem (@PathVariable Long accountId, @RequestBody CreateItemRequest req) throws SQLException {
        return itemService.createItem(accountId, req.itemName(), req.cost(), req.amt());
    }
}
