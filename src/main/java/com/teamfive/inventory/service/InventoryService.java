package com.teamfive.inventory.service;

import com.teamfive.inventory.entity.Inventory;


import java.util.ArrayList;

public interface InventoryService {

    public Inventory addInventory(Inventory inventory);
    public void deleteInventory(String uId);
    public Inventory updateInventory(Inventory inventory);
    public Inventory selectInventory(String uId);
    public ArrayList<Inventory> findByProductId(String productId);
    public void decrementQuantity(String inventoryId,int quantity);

}
