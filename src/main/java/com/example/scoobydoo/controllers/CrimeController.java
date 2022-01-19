package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrimeController {
    @Autowired
    private CrimeService crimeService;
    @GetMapping()
    public String getMainPage(Model model, @AuthenticationPrincipal UserDetails profile) {
        model.addAttribute("crimes", crimeService.getAllCrimes(profile));
        return "main";
    }
}
