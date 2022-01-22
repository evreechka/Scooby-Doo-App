package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Contention;
import com.example.scoobydoo.services.ContentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/contention")
public class ContentionController {
    @Autowired
    private ContentionService contentionService;
    @GetMapping("/add")
    private String getAddContentionPage() {
        return "add_contention";
    }
    @PostMapping("/add")
    private String createContention(Model model, @RequestParam String description, @RequestParam String damageCritically, @RequestParam String characterId) {
        Map<String, Object> feedback = contentionService.createContention(description, damageCritically, characterId);
        if (feedback.get("success") != null) {
            Contention contention = (Contention) feedback.get("success");
            return "redirect:/crime/" + contention.getId() + "/add";
        }
        model.mergeAttributes(feedback);
        return "add_contention";
    }
}
