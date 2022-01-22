package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.Suspect;
import com.example.scoobydoo.services.CharacterService;
import com.example.scoobydoo.services.SuspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/suspect")
public class SuspectController {
    @Autowired
    private SuspectService suspectService;
    @Autowired
    private CharacterService characterService;

    @GetMapping("/{crimeVisitId}/add")
    public String getAddSuspectPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("users", characterService.getAllCharacters());
        model.addAttribute("crimeVisitId", crimeVisitId);

        return "add_suspect";
    }

    @PostMapping("/{crimeVisitId}/add")
    public String addSuspect(@PathVariable(name = "crimeVisitId") CrimeVisit crimeVisit, @Valid Suspect suspect, BindingResult bindingResult, Model model, @RequestParam String userId) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            suspectService.addSuspect(crimeVisit, suspect, userId);
            return "redirect:/crime_visit/" + crimeVisit.getId();
        }
        return "add_suspect";
    }
}
