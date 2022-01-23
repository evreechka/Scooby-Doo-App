package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CriminalCase;
import com.example.scoobydoo.entities.Monster;
import com.example.scoobydoo.services.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/criminal_case")
public class CriminalCaseController {
    @Autowired
    private CriminalCaseService criminalCaseService;
    @GetMapping("/{criminalCaseId}")
    public String getCriminalCase(@PathVariable long criminalCaseId, Model model) {
        model.addAttribute("criminal_case", criminalCaseService.getCriminalCaseById(criminalCaseId));
        model.mergeAttributes(criminalCaseService.getSeverityGradation(criminalCaseId));
        return "criminal_case";
    }
    @GetMapping("/{crimeId}/add")
    public String getAddCriminalCasePage(@PathVariable long crimeId, Model model) {
        model.addAttribute("crimeId", crimeId);
        return "add_criminal_case";
    }
    @PostMapping("/{criminalCaseId}/close")
    public String closeCriminalCase(@PathVariable long criminalCaseId, Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        model.mergeAttributes(criminalCaseService.closeCriminalCase(criminalCaseId, profileDetails));
        model.addAttribute("criminal_case", criminalCaseService.getCriminalCaseById(criminalCaseId));
        model.mergeAttributes(criminalCaseService.getSeverityGradation(criminalCaseId));
        return "criminal_case";
    }

    @PostMapping("/{crimeId}/add")
    public String addCriminalCase(@PathVariable(name = "crimeId") Crime crime, @Valid CriminalCase criminalCase, BindingResult criminalCaseBindingResult, @Valid Monster monster, BindingResult monsterBindingResult, @RequestParam String type, Model model) {
        if (criminalCaseBindingResult.hasErrors() || monsterBindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(criminalCaseBindingResult));
            model.mergeAttributes(getErrors(monsterBindingResult));
        } else {
            criminalCaseService.addCriminalCase(crime, monster, type, criminalCase);
            //criminalCaseService.createNotification();
            return "redirect:/crime/" + crime.getId();
        }
        return "add_criminal_case";
    }
}
