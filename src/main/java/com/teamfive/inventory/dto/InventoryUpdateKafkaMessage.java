package com.teamfive.inventory.dto;

import com.teamfive.inventory.entity.Inventory;

public class InventoryUpdateKafkaMessage {
    private String action;
    private Inventory inventory;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "InventoryUpdateKafkaMessage{" +
                "action='" + action + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
