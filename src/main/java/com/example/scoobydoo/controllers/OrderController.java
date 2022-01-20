package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/make/{id}")
    public String order(Model model, @PathVariable long id) {
        orderService.makeOrder(id);
        return "redirect:/profile/" + id;
    }
}
