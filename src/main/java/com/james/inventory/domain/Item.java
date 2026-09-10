package com.james.inventory.domain;

public class Item {
    private final Long itemId;
    private final String itemName;

    public Item (Long itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return itemName;
    }
}
