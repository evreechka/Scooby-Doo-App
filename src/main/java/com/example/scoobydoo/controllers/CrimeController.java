package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.services.CharacterService;
import com.example.scoobydoo.services.CrimeSceneService;
import com.example.scoobydoo.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
public class CrimeController {
    @Autowired
    private CrimeService crimeService;
    @Autowired
    private CrimeSceneService crimeSceneService;
    @Autowired
    private CharacterService characterService;

    @GetMapping()
    public String getMainPage(Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        model.addAttribute("crimes", crimeService.getAllCrimes(profileDetails));
        return "main";
    }

    @GetMapping("/crime/{crimeId}")
    public String getCrimeInfo(@PathVariable long crimeId, Model model) {
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        model.addAttribute("damage", crimeService.getDamage(crimeId));
        model.addAttribute("victim_homes", crimeService.getVictimHomes(crimeId));
        return "crime";
    }

    @GetMapping("/crime/add")
    public String getAddCrimePage(Model model) {
        model.addAttribute("crime_scenes", crimeSceneService.getAllCrimeScene());
        model.addAttribute("sheriffs", characterService.getSheriffs());
        model.addAttribute("investigators", crimeService.getInvestigators());
        return "add_crime";
    }
//TODO
    @PostMapping("/crime/{crimeId}/close")
    public String closeCrime(@PathVariable long crimeId, Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        Map<String, String> feedback = crimeService.closeCrime(crimeId, profileDetails);
        model.mergeAttributes(feedback);
        model.addAttribute("crime", crimeService.getCrime(crimeId));
        model.addAttribute("damage", crimeService.getDamage(crimeId));
        model.addAttribute("victim_homes", crimeService.getVictimHomes(crimeId));
        return "crime";
    }

    @PostMapping("/crime/add")
    public String createCrime(@Valid Crime crime, BindingResult crimeBindingResult, @Valid Character victim, BindingResult victimBindingResult, @Valid Contention contention, BindingResult contentionBindingResult, @Valid Monster monster, BindingResult monsterBindingResult, @Valid CriminalCase criminalCase, BindingResult criminalCaseBindingResult, @Valid CrimeVisit crimeVisit, BindingResult crimeVisitBindingResult, @RequestParam String sheriffId, @RequestParam String[] invIds, @RequestParam String crimeSceneId, @RequestParam String type, Model model, @AuthenticationPrincipal UserDetails profileDetails) {
        if (crimeBindingResult.hasErrors() || victimBindingResult.hasErrors() || contentionBindingResult.hasErrors() || monsterBindingResult.hasErrors() || criminalCaseBindingResult.hasErrors() || crimeVisitBindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(crimeBindingResult));
            model.mergeAttributes(getErrors(victimBindingResult));
            model.mergeAttributes(getErrors(contentionBindingResult));
            model.mergeAttributes(getErrors(monsterBindingResult));
            model.mergeAttributes(getErrors(criminalCaseBindingResult));
            model.mergeAttributes(getErrors(crimeVisitBindingResult));
            return getAddCrimePage(model);
        } else {
            crimeService.createCrime(crime, victim, contention, monster, criminalCase, crimeVisit, sheriffId, crimeSceneId, invIds, type);
            return getMainPage(model, profileDetails);
        }
    }
}
