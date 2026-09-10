package com.james.inventory.domain;

// Initializes an Item Java Object.

import java.lang.IllegalArgumentException;

public class Item {
    private final Long item_id;
    private String item_name;
    private final Long account_id;
    private Long amt;

    public Item (Long item_id, String item_name, Long account_id, Long amt) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.account_id = account_id;
        this.amt = amt;
    }

    public Long getItemId () {
        return this.item_id;
    }

    public String getItemName () {
        return this.item_name;
    }
    public void setItemName (String item_name) {
        this.item_name = item_name;
    }

    public Long getAccountId () {
        return this.account_id;
    }

    public Long getAmt () {
        return this.amt;
    }

    public void setAmt (long amt) throws IllegalArgumentException {
        if (amt < 0) {
            throw new IllegalArgumentException("ERROR: Unable to set amt to set ammount to do it being invalid.");
        } else {
            this.amt = amt;
        }
    }

    public void receive (long amt) {
        setAmt(this.amt += amt);
    }
    public void sell (long amt) throws IllegalArgumentException{
        if (this.amt - amt < 0) { 
            throw new IllegalArgumentException("ERROR: Unable to sell the inputted amount.");
        } else {
            setAmt(this.amt -= amt);
        }
    }
}
