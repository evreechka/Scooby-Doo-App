package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.CriminalCase;
import com.example.scoobydoo.services.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/{crimeId}/{monsterId}/add")
    public String getAddCriminalCasePage(@PathVariable long crimeId, @PathVariable long monsterId, Model model) {
        model.addAttribute("monsterId", monsterId);
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

    @PostMapping("/{crimeId}/{monsterId}/add")
    public String addCriminalCase(@PathVariable long monsterId, @PathVariable long crimeId, @Valid CriminalCase criminalCase, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            criminalCaseService.addCriminalCase(crimeId, monsterId, criminalCase);
            return "redirect:/crime/" + crimeId;
        }
        return "add_criminal_case";
    }
}
