package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.util.Map;

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
}
