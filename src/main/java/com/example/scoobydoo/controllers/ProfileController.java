package com.example.scoobydoo.controllers;

import com.example.scoobydoo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/{profileId}")
    public String getInvestigatorProfile(@PathVariable long profileId, Model model) {
        Map<String, Object> profileParams = profileService.getInvestigatorProfile(profileId);
        for (String key: profileParams.keySet()) {
            model.addAttribute(key, profileParams.get(key));
        }
        return "profile";
    }
}
