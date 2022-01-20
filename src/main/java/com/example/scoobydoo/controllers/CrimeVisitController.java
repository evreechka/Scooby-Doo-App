package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.CrimeService;
import com.example.scoobydoo.services.CrimeVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/crime_visit")
public class CrimeVisitController {
    @Autowired
    private CrimeVisitService crimeVisitService;
    @Autowired
    private CrimeService crimeService;
    @GetMapping("/{crimeVisitId}")
    public String getCrimeVisitPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("crime_visit", crimeVisitService.getCrimeVisit(crimeVisitId));
        return "crime_visit";
    }
    @GetMapping("/{crimeId}/add")
    public String getCrimeVisitAddPage(@PathVariable long crimeId, Model model) {
        model.addAttribute("crimeId", crimeId);
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        return "add_crime_visit";
    }
    @PostMapping("/{crimeId}/add")
    public String getCrimeVisitAddPage(@PathVariable long crimeId, @RequestParam String severityDestruction, Model model) {
        Map<String, String> feedback = crimeVisitService.addCrimeVisit(crimeId, severityDestruction);
        if (feedback == null)
            return "redirect:/crime/" + crimeId;
        model.mergeAttributes(feedback);
        return "add_crime_visit";
    }

}
