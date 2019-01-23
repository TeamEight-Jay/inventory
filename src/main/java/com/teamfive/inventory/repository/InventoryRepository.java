package com.teamfive.inventory.repository;


import com.teamfive.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InventoryRepository extends CrudRepository <Inventory,String>{


    public ArrayList<Inventory> findByProductId(String productId);

    //@Query(value = "UPDATE Inventory SET quantitySold=quantitySold+?1 quantityLeft=quantityLeft+?1 WHERE inventory_id=?0 ",nativeQuery = false)
    //public void decrementQuantity(String inventoryId,int quantity);


}
