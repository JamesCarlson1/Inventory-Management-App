package com.james.inventory.ui;

public record CreateItemRequest (Long accountId, String itemName, long cost, long amt) {
}
