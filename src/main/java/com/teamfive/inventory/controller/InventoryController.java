package com.teamfive.inventory.controller;


import com.teamfive.inventory.dto.InventoryDTO;
import com.teamfive.inventory.entity.Inventory;
import com.teamfive.inventory.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {
@Autowired
    InventoryService inventoryService;


@RequestMapping("/addinventory")
public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO){
    Inventory inventory=new Inventory();
    BeanUtils.copyProperties(inventoryDTO,inventory);
    inventoryService.addInventory(inventory);
    return inventoryDTO;
}


@RequestMapping("/selectinventory")
public Inventory selectInventory(@PathVariable String uId){
    return inventoryService.selectInventory(uId);


}

@RequestMapping("/updateinventory")
public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO){

    Inventory inventory=new Inventory();
    BeanUtils.copyProperties(inventoryDTO,inventory);
    inventoryService.updateInventory(inventory);
    return inventoryDTO;

}

@RequestMapping("/deleteinventory")
public void deleteInventory(@PathVariable String uId){
    inventoryService.deleteInventory(uId);


}



}
