package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.OrderService;
import com.example.scoobydoo.services.ProfileService;
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
    @Autowired
    ProfileService profileService;

    @PostMapping("/make/{criminal_case_id}")
    public String createInventory(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable long criminal_case_id, @RequestParam String name) {
        orderService.inventorySelection(criminal_case_id, userDetails, name);
        model.addAttribute("criminalCaseId", criminal_case_id);
        return "redirect:/profile/" + profileService.getProfileByUsername(userDetails.getUsername()).getId();
    }


//    @PostMapping("/make/{criminal_case_id}")
//    public String makeOrder(Model model, @AuthenticationPrincipal UserDetails userDetails, String name, @PathVariable Long criminal_case_id) {
//        try {
//            if (orderService.makeOrder(userDetails, name, criminal_case_id))
//                return "redirect:/criminal_case/" + criminal_case_id;
//            else return "redirect:/order/inventory/" + criminal_case_id;
//        } catch (Exception e) {
//            return "redirect:/order/inventory/" + criminal_case_id;
//        }
//    }
}
