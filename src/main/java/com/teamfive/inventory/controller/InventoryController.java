package com.teamfive.inventory.controller;


import com.teamfive.inventory.dto.InventoryDTO;
import com.teamfive.inventory.dto.MerchantDTO;
import com.teamfive.inventory.entity.Inventory;
import com.teamfive.inventory.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class InventoryController {

    private final String MERCHANT_ENDPOINT="http://localhost:5000/";

    @Autowired
    InventoryService inventoryService;


    @PostMapping("/inventory/add")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO){
        Inventory inventory=new Inventory();
        BeanUtils.copyProperties(inventoryDTO,inventory);

        RestTemplate template=new RestTemplate();
        String merchantCheckEndpoint=MERCHANT_ENDPOINT+"merchant/get?merchantId="+inventory.getMerchantId();

        MerchantDTO merchantDTO=template.getForEntity(merchantCheckEndpoint,MerchantDTO.class).getBody();

        if(merchantDTO.getMerchantId()==null) return new InventoryDTO();

        inventory.setMerchantName(merchantDTO.getMerchantName());
        inventory.setQuantitySold(0);
        inventory.setInventoryRating(merchantDTO.getMerchantRating());

        inventoryService.addInventory(inventory);


        return inventoryDTO;
    }



    @GetMapping("/inventory/get/{uId}")
    public InventoryDTO selectInventory(@PathVariable String uId){
        Inventory inventory=inventoryService.selectInventory(uId);
        if(inventory==null) return null;
        InventoryDTO inventoryDTO=new InventoryDTO();
        BeanUtils.copyProperties(inventory,inventoryDTO);
        return inventoryDTO;
    }

    @PostMapping("/inventory/update")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO){

        Inventory inventory=new Inventory();
        BeanUtils.copyProperties(inventoryDTO,inventory);
        inventoryService.updateInventory(inventory);
        return inventoryDTO;

    }

    @DeleteMapping("/inventory/delete/{uId}")
    public void deleteInventory(@PathVariable String uId){
        inventoryService.deleteInventory(uId);
    }

    @GetMapping("inventory/{productId}")
    public ArrayList<Inventory> getMerchants(@PathVariable String productId)
    {
        return inventoryService.findByProductId(productId);
    }

    @GetMapping("inventory/placeorder/{inventoryId}/{quantity}")
    public String placeorder(@PathVariable String inventoryId,@PathVariable Integer quantity)
    {
        return inventoryService.decrementQuantity(inventoryId,quantity);
    }


}
