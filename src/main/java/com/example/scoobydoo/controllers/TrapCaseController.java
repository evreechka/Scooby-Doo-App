package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.TrapCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trap")
public class TrapCaseController {
    @Autowired
    private TrapCaseService trapCaseService;
    @GetMapping()
    public String getAllTraps(Model model) {
        model.addAttribute("traps", trapCaseService.getAllTraps());
        return "trap_list";
    }
}
