package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CharacterService;
import com.example.scoobydoo.services.InvestigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/investigator")
public class InvestigatorController {
    @Autowired
    private InvestigatorService investigatorService;
    @Autowired
    private CharacterService characterService;

    @GetMapping()
    public String getAllInvestigators(Model model) {
        model.addAttribute("users", investigatorService.getAllInvestigators());
        return "users_list";
    }
    @GetMapping("/add")
    public String getAddInvestigatorPage(Model model) {
        model.addAttribute("users", characterService.getUsers());
        return "add_investigator";
    }
    @PostMapping("/add")
    public String addInvestigator(@RequestParam String userId, @RequestParam String feature) {
        investigatorService.addInvestigator(userId, feature);
        return "redirect:/investigator";
    }
    @PostMapping("/{investigatorId}/delete")
    public String deleteInvestigator(@PathVariable long investigatorId) {
        investigatorService.deleteInvestigator(investigatorId);
        return "redirect:/investigator";
    }
}
