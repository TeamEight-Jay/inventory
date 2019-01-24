package com.teamfive.inventory.dto;

import com.teamfive.inventory.entity.Inventory;

public class InventoryUpdateKafkaMessage {
    private String action;
    private String message;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InventoryUpdateKafkaMessage{" +
                "action='" + action + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
