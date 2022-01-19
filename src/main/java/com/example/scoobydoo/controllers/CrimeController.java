package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "crime";
    }
}
