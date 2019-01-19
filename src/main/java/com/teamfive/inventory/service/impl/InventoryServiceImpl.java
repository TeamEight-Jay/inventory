package com.teamfive.inventory.service.impl;

import com.teamfive.inventory.entity.Inventory;
import com.teamfive.inventory.repository.InventoryRepository;
import com.teamfive.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly  =true,propagation = Propagation.REQUIRES_NEW)
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    InventoryRepository inventoryRepository;


    @Override
    @Transactional(readOnly = false)
    public Inventory addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInventory(String uId) {

        inventoryRepository.delete(uId);

    }

    @Override
    @Transactional(readOnly = false)
    public Inventory updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);

        return inventory;
    }

    @Override
    public Inventory selectInventory(String uId) {
        return inventoryRepository.findOne(uId);
    }
}

