package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping()
    public String getAllCharacters(Model model) {
        model.addAttribute("characters", characterService.getAllCharacters());
        return "character_list";
    }

    @GetMapping("/add")
    public String getAddCharacterPage() {
        return "add_character";
    }

    @PostMapping("/add")
    public String saveCharacter(@Valid Character character, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResult));
        } else {
            characterService.createCharacter(character);
            return "redirect:/character";
        }
        return "add_character";
    }
}
