package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CrimeController {
    @Autowired
    private CrimeService crimeService;
    @GetMapping()
    public String getMainPage(Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        model.addAttribute("crimes", crimeService.getAllCrimes(profileDetails));
        return "main";
    }
    @GetMapping("/crime/{crimeId}")
    public String getCrimeInfo(@PathVariable long crimeId, Model model) {
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        model.addAttribute("damage", crimeService.getDamage(crimeId));
        model.addAttribute("victim_homes",crimeService.getVictimHomes(crimeId));
        return "crime";
    }
    @GetMapping("/crime/{contentionId}/add")
    public String getAddCrimePage(@PathVariable long contentionId, Model model) {
        model.addAttribute("contentionId", contentionId);
        model.addAttribute("investigators", crimeService.getInvestigators());
        return "add_crime";
    }
    @PostMapping("/crime/{crimeId}/close")
    public String closeCrime(@PathVariable long crimeId, Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        Map<String, String> feedback = crimeService.closeCrime(crimeId, profileDetails);
        model.mergeAttributes(feedback);
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        model.addAttribute("damage", crimeService.getDamage(crimeId));
        model.addAttribute("victim_homes",crimeService.getVictimHomes(crimeId));
        return "crime";
    }
    @PostMapping("/crime/{contentionId}/add")
    public String createCrime(@PathVariable long contentionId, Model model, @RequestParam String description, @RequestParam String fee, @RequestParam String sheriffId, @RequestParam String[] invIds) {
        Map<String, String> feedback = crimeService.createCrime(contentionId, description, fee, sheriffId, invIds);
        if (feedback == null)
            return "redirect:/crime";
        model.mergeAttributes(feedback);
        model.addAttribute("contentionId", contentionId);
        model.addAttribute("investigators", crimeService.getInvestigators());
        return "add_crime";
    }
}
