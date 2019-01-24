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
        InventoryUpdateKafkaMessage inventoryUpdateKafkaMessage=new InventoryUpdateKafkaMessage();
        inventoryUpdateKafkaMessage.setAction("INSTOCK");
        inventoryUpdateKafkaMessage.setMessage(inventory.getProductId()+"|"
                +inventory.getInventoryId() +"|"
                +inventory.getInventoryRating() +"|"
                +inventory.getPrice());
        inventoryKafka.send("INVENTORY",inventoryUpdateKafkaMessage);
        return inventory;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInventory(String uId) {
        Inventory inventory=this.selectInventory(uId);
        if(inventory==null) return;
        inventoryRepository.delete(uId);
        InventoryUpdateKafkaMessage inventoryUpdateKafkaMessage=new InventoryUpdateKafkaMessage();
        inventoryUpdateKafkaMessage.setAction("OUTOFSTOCK");
        inventoryUpdateKafkaMessage.setMessage(inventory.getProductId()+"|"+inventory.getInventoryId());
        inventoryKafka.send("INVENTORY",inventoryUpdateKafkaMessage);

    }

    @Override
    @Transactional(readOnly = false)
    public Inventory updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        InventoryUpdateKafkaMessage inventoryUpdateKafkaMessage=new InventoryUpdateKafkaMessage();
        inventoryUpdateKafkaMessage.setAction("INSTOCK");
        inventoryUpdateKafkaMessage.setMessage(inventory.getProductId()+"|"
                +inventory.getInventoryId() +"|"
                +inventory.getInventoryRating() +"|"
                +inventory.getPrice());
        inventoryKafka.send("INVENTORY",inventoryUpdateKafkaMessage);
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
    public String decrementQuantity(String inventoryId, int quantity) {

        Inventory inventory=this.selectInventory(inventoryId);

        if(inventory==null) return "ITEM NOT FOUND";

        if(quantity>inventory.getQuantityLeft()) return "MAX AVAILABLE QUANTITY IS "+inventory.getQuantityLeft();

        inventory.setQuantitySold(inventory.getQuantitySold()+quantity);
        inventory.setQuantityLeft(inventory.getQuantityLeft()-quantity);
        inventory=this.updateInventory(inventory);
        if(inventory.getQuantityLeft()==0)
        {
            InventoryUpdateKafkaMessage inventoryUpdateKafkaMessage=new InventoryUpdateKafkaMessage();
            inventoryUpdateKafkaMessage.setAction("OUTOFSTOCK");
            inventoryUpdateKafkaMessage.setMessage(inventory.getProductId()+"|"
                    +inventory.getInventoryId());
            inventoryKafka.send("INVENTORY",inventoryUpdateKafkaMessage);
        }

        return "SUCCESS";
    }


}

