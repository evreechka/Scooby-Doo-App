package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping()
    public String getAllItems(Model model) {
        model.addAttribute("items", inventoryService.getAllItems());
        return "inventory";
    }
}
