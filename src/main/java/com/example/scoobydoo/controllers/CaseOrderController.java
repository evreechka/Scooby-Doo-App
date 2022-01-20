package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class CaseOrderController {
    @Autowired
    private CaseOrderService caseOrderService;
    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable long orderId, Model model) {
        model.addAttribute("order", caseOrderService.getCaseOrderByID(orderId));
        return "order";
    }
}
