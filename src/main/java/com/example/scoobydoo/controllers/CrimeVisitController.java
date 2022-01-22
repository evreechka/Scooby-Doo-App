package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CrimeScene;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.services.CrimeSceneService;
import com.example.scoobydoo.services.CrimeService;
import com.example.scoobydoo.services.CrimeVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/crime_visit")
public class CrimeVisitController {
    @Autowired
    private CrimeVisitService crimeVisitService;
    @Autowired
    private CrimeService crimeService;
    @Autowired
    private CrimeSceneService crimeSceneService;

    @GetMapping("/{crimeVisitId}")
    public String getCrimeVisitPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("crime_visit", crimeVisitService.getCrimeVisit(crimeVisitId));
        return "crime_visit";
    }

    @GetMapping("/{crimeId}/add")
    public String getCrimeVisitAddPage(@PathVariable long crimeId, Model model) {
        model.addAttribute("crime_scenes", crimeSceneService.getAllCrimeScene());
        model.addAttribute("crimeId", crimeId);
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        return "add_crime_visit";
    }

    @PostMapping("/{crimeId}/add")
    public String addCrimeVisit(@PathVariable(name = "crimeId") Crime crime, @Valid CrimeVisit crimeVisit, BindingResult bindingResult, @RequestParam String crimeSceneId, Model model) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            String[] roles = new String[]{"CLUE_SEARCHER", "VICTIM_INTERVIEW", "CRIME_SCENE_INSPECTOR"};
            crimeVisitService.addCrimeVisit(crime, crimeVisit, crimeSceneId, roles);
            return "redirect:/crime/" + crime.getId();
        }
        return "add_crime_visit";
    }

}
