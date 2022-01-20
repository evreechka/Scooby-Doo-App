package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Inventory;
import com.example.scoobydoo.entities.TrapCase;
import com.example.scoobydoo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    private int page_counter = 0;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping()
    public String getAllItems(Model model, @RequestParam(required = false, defaultValue = "0") int x) {
        model.addAttribute("object", this);
        model.addAttribute("page_counter", page_counter);
        return "inventory";
    }

    public List<Inventory> getPage(int x) {
        return inventoryService.getAllItems(x, 50);
    }

    public void increment() {
        page_counter++;
    }
}
