package com.teamfive.inventory.repository;


import com.teamfive.inventory.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository <Inventory,String>{




}
