package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Monster;
import com.example.scoobydoo.services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/monster")
public class MonsterController {
    @Autowired
    private MonsterService monsterService;
    @GetMapping()
    public String getAllMonsters(Model model) {
        model.addAttribute("monsters", monsterService.getAllMonsters());
        return "monsters_list";
    }
    @GetMapping("{crimeId}/add")
    public String getAddMonsterPage(@PathVariable long crimeId, Model model) {
        model.addAttribute("crimeId", crimeId);
        return "add_monster";
    }
    @PostMapping("{crimeId}/add")
    public String createMonster(@PathVariable long crimeId, @Valid Monster monster, BindingResult bindingResult, Model model, @RequestParam String type) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            long monsterId = monsterService.createMonster(monster, type).getId();
            String url = "/criminal_case/" + crimeId + "/" + monsterId + "/add";
            return "redirect:" + url;
        }
        return "add_monster";
    }
}
