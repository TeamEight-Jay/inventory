package com.teamfive.inventory.controller;


import com.teamfive.inventory.dto.InventoryDTO;
import com.teamfive.inventory.dto.MerchantDTO;
import com.teamfive.inventory.dto.ProductDTO;
import com.teamfive.inventory.entity.Inventory;
import com.teamfive.inventory.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class InventoryController {
@Autowired
    InventoryService inventoryService;


@PostMapping("/inventory/add")
public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO){
    Inventory inventory=new Inventory();

    ProductDTO productDTO= getProduct(inventoryDTO.getProductId());
    MerchantDTO merchantDTO= getMerchant(inventoryDTO.getMerchantId());

    BeanUtils.copyProperties(inventoryDTO,inventory);
    inventoryService.addInventory(inventory);
    return inventoryDTO;
}
    private ProductDTO getProduct(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/product/get/"+productId;
        ResponseEntity<ProductDTO> response
                = restTemplate.getForEntity(fooResourceUrl , ProductDTO.class);
        return response.getBody();
    }



    private MerchantDTO getMerchant(String merchantId)
    {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/getMerchant/"+merchantId;
        ResponseEntity<MerchantDTO> response
                = restTemplate.getForEntity(fooResourceUrl , MerchantDTO.class);
        return response.getBody();
    }

@GetMapping("/inventory/select")
public Inventory selectInventory(@PathVariable String uId){
    return inventoryService.selectInventory(uId);


}

@PostMapping("/inventory/update")
public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO){

    Inventory inventory=new Inventory();
    BeanUtils.copyProperties(inventoryDTO,inventory);
    inventoryService.updateInventory(inventory);
    return inventoryDTO;

}

@PostMapping("/inventory/delete")
public void deleteInventory(@PathVariable String uId){
    inventoryService.deleteInventory(uId);


}



}
