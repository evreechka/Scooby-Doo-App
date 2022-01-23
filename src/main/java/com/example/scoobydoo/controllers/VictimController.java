package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.services.CharacterService;
import com.example.scoobydoo.services.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/victim")
public class VictimController {
    @Autowired
    private VictimService victimService;
    @Autowired
    private CharacterService characterService;
    @GetMapping("/{crimeVisitId}/add")
    public String getAddVictimPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("users", characterService.getAllCharacters());
        model.addAttribute("crimeVisitId", crimeVisitId);
        return "add_victim";
    }

    @PostMapping("/{crimeVisitId}/add")
    public String addVictim(@PathVariable(name = "crimeVisitId")CrimeVisit crimeVisit, Model model, @Valid Character character, BindingResult bindingResult, @RequestParam String indication) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            Map<String, String> feedback = victimService.addVictim(indication, character, crimeVisit);
            if (feedback == null) {
                return "redirect:/crime_visit/" + crimeVisit.getId();
            }
            model.mergeAttributes(feedback);
        }
        return "add_victim";
    }
}
