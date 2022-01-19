package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.InvestigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investigator")
public class InvestigatorController {
    @Autowired
    private InvestigatorService investigatorService;

    @GetMapping()
    public String getAllInvestigators(Model model) {
        model.addAttribute("users", investigatorService.getAllInvestigators());
        return "users_list";
    }
}
