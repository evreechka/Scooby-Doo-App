package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Clue;
import com.example.scoobydoo.services.ClueService;
import com.example.scoobydoo.services.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private ClueService clueService;

    @GetMapping("/{criminalCaseId}/add")
    public String getAddCluePage(@PathVariable long criminalCaseId, Model model) {
        model.addAttribute("crimeVisits", clueService.getClueCriminalCase(criminalCaseId).getCrime().getCrimeVisits());
        model.addAttribute("criminalCaseId", criminalCaseId);
        return "add_clue";
    }

    @GetMapping("/{criminalCaseId}/{clueId}/add_suspects")
    public String getAddSuspectsPage(@PathVariable long criminalCaseId, @PathVariable long clueId, Model model) {
        model.addAttribute("clueId", clueId);
        model.addAttribute("criminalCase", clueService.getClueCriminalCase(criminalCaseId));
        return "add_clue_suspects";
    }

    @PostMapping("/{criminalCaseId}/add")
    public String addClue(@PathVariable long criminalCaseId, @Valid Clue clue, BindingResult bindingResult, Model model, @RequestParam String crimeVisitId) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            clueService.saveClue(clue, criminalCaseId, crimeVisitId);
            return "redirect:/criminal_case/" + criminalCaseId;
        }
        return "add_clue";
    }

    @PostMapping("/{criminalCaseId}/{clueId}/add_suspects")
    public String addSuspect(@PathVariable long criminalCaseId, @PathVariable long clueId, Model model, @RequestParam String[] suspectIds) {
        clueService.addSuspects(clueId, suspectIds);
        return "redirect:/criminal_case/" + criminalCaseId;
    }
}
