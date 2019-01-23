package com.teamfive.inventory.service.impl;

import com.teamfive.inventory.dto.InventoryUpdateKafkaMessage;
import com.teamfive.inventory.entity.Inventory;
import com.teamfive.inventory.repository.InventoryRepository;
import com.teamfive.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    KafkaTemplate<String,InventoryUpdateKafkaMessage> inventoryKafka;


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
    @Transactional(readOnly = true)
    public Inventory selectInventory(String uId) {
        return inventoryRepository.findOne(uId);
    }

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Inventory> findByProductId(String productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    @Transactional(readOnly = false)
    public void decrementQuantity(String inventoryId, int quantity) {
        Inventory inventory=this.selectInventory(inventoryId);
        inventory.setQuantitySold(inventory.getQuantitySold()+quantity);
        inventory.setQuantityLeft(inventory.getQuantitySold()-quantity);
        inventory=this.updateInventory(inventory);
        if(inventory.getQuantityLeft()==0)
        {
            InventoryUpdateKafkaMessage inventoryUpdateKafkaMessage=new InventoryUpdateKafkaMessage();
            inventoryUpdateKafkaMessage.setInventory(inventory);
            inventoryUpdateKafkaMessage.setAction("OUTOFSTOCK");
            inventoryKafka.send("INVENTORY",inventoryUpdateKafkaMessage);
        }
        //inventoryRepository.decrementQuantity(inventoryId,quantity);
    }


}

