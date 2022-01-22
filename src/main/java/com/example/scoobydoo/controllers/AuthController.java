package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import javax.validation.Valid;
import java.util.Map;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
public class AuthController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null)
            model.addAttribute("error", "Incorrect username or password");
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Character character,
                               BindingResult bindingResultCharacter,
                               @Valid Profile profile,
                               BindingResult bindingResultProfile,
                               Model model) {
        if (bindingResultCharacter.hasErrors() || bindingResultProfile.hasErrors()) {
            model.mergeAttributes(getErrors(bindingResultCharacter));
            model.mergeAttributes(getErrors(bindingResultProfile));
        } else {
            profileService.createProfile(profile, character);
            model.addAttribute("success", "You successfully registered. Write your profile data!");
            return "redirect:/login";
        }
        return "register";
    }
}
