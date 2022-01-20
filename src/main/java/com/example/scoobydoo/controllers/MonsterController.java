package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Monster;
import com.example.scoobydoo.services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/add")
    public String getAddMonsterPage() {
        return "add_monster";
    }
    @PostMapping("/add")
    public String createMonster(@Valid Monster monster, BindingResult bindingResult, Model model, @RequestParam String type) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            monsterService.createMonster(monster, type);
            return "redirect:/monster";
        }
        return "add_monster";
    }
}
