package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Suspect;
import com.example.scoobydoo.entities.Victim;
import com.example.scoobydoo.services.SuspectService;
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

    @GetMapping("/{crimeVisitId}/add")
    public String getAddVictimPage(@PathVariable long crimeVisitId, Model model) {
        model.addAttribute("crimeVisitId", crimeVisitId);
        return "add_victim";
    }

    @PostMapping("/{crimeVisitId}/add")
    public String addSuspect(@PathVariable long crimeVisitId, Model model, @RequestParam String id, @RequestParam String indication) {
        Map<String, String> feedback = victimService.addVictim(indication, id, crimeVisitId);
        if (feedback == null) {
            return "redirect:/crime_visit/" + crimeVisitId;
        }
        model.mergeAttributes(feedback);
        return "add_victim";
    }
}
