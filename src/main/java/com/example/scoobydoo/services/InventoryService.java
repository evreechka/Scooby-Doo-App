package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Inventory;
import com.example.scoobydoo.repos.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;
    public List<Inventory> getAllItems() {
        return inventoryRepo.findAll();
    }
}
