package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Suspect;
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
    @GetMapping("/{crimeVisitId}/add")
    public String getAddSuspectPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("crimeVisitId", crimeVisitId);
        return "add_suspect";
    }
    @PostMapping("/{crimeVisitId}/add")
    public String addSuspect(@PathVariable long crimeVisitId, @Valid Suspect suspect, BindingResult bindingResult, Model model, @RequestParam String id) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            Map<String, String> feedback = suspectService.addSuspect(crimeVisitId, suspect, id);
            if (feedback == null) {
                return "redirect:/crime_visit/" + crimeVisitId;
            }
            model.mergeAttributes(feedback);
        }
        return "add_suspect";
    }
}
