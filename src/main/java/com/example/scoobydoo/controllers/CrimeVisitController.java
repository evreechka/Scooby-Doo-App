package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.services.CrimeVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crime_visit")
public class CrimeVisitController {
    @Autowired
    private CrimeVisitService crimeVisitService;
    @GetMapping("/{crimeVisitId}")
    public String getCrimeVisit(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("crime_visit", crimeVisitService.getCrimeVisit(crimeVisitId));
        return "crime_visit";
    }

}
