package com.teamfive.inventory.service;

import com.teamfive.inventory.dto.InventoryDTO;
import com.teamfive.inventory.entity.Inventory;

public interface InventoryService {

    public Inventory addInventory(Inventory inventory);
    public void deleteInventory(String uId);
    public Inventory updateInventory(Inventory inventory);
    public Inventory selectInventory(String uId);



}
