package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/inventory/{investigator_id}/{crime_id}")
    public Map<String, String> createInventory(Model model, @PathVariable long investigator_id, @PathVariable long crime_id) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("inventory", orderService.inventorySelection(investigator_id, crime_id));
        return params;
    }

    @PostMapping("/order/{investigator_id}")
    public Map<String, String> createInventory(Model model, @PathVariable long investigator_id, @RequestParam List<Long> ids) {
        HashMap<String, String> params = new HashMap<>();
        params.put("error", orderService.makeOrder(investigator_id, ids) ? "true" : "false");
        return params;
    }

}
