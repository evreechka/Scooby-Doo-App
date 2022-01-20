package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Inventory;
import com.example.scoobydoo.repos.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public List<Inventory> getAllItems(int x, int y) {
        return inventoryRepo.findAll(PageRequest.of(x, y)).getContent();
    }
}
