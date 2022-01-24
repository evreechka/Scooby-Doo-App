package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/inventory/{criminal_case_id}")
    public String createInventory(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable long criminal_case_id, @RequestParam String name) {
        orderService.inventorySelection(criminal_case_id, userDetails, name);
        model.addAttribute("criminalCaseId", criminal_case_id);
        return "redirect:/criminal_case/" + criminal_case_id;
    }


//    @PostMapping("/make/{criminal_case_id}")
//    public String makeOrder(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "default") String name, @RequestParam(required = false) Map<String, String> itemCount, @PathVariable Long criminal_case_id) {
//        try {
//            if (orderService.makeOrder(userDetails, name, itemCount, criminal_case_id))
//                return "redirect:/criminal_case/" + criminal_case_id;
//            else return "redirect:/order/inventory/" + criminal_case_id;
//        } catch (Exception e) {
//            return "redirect:/order/inventory/" + criminal_case_id;
//        }
//    }
}
