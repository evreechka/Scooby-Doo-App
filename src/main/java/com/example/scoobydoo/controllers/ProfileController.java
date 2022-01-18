package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public String getInvestigatorProfile(@PathVariable long profileId, Model model) {
        Map<String, Object> profileParams = profileService.getInvestigatorProfile(profileId);
        for (String key : profileParams.keySet()) {
            model.addAttribute(key, profileParams.get(key));
        }
        return "profile";
    }

    @GetMapping("/{profileId}/edit")
    public String getEditPage(@PathVariable long profileId, Model model) {
        model.addAttribute("user_profile", profileService.getProfile(profileId));
        return "edit_profile";
    }

    @PostMapping("/{profileId}/edit")
    public String editProfile(@PathVariable long profileId, Model model, @RequestParam(required = false) MultipartFile file, @RequestParam(required = false) String password, @RequestParam(required = false) String username) throws IOException {
        Map<String, String> feedback;
        try {
            feedback = profileService.saveChanges(profileId, username, password, file);
            System.out.println(feedback.size());
            if (feedback.isEmpty()) {
                model.addAttribute("success", "Changes are successfully saved!");
                model.addAttribute("user_profile", profileService.getProfile(profileId));
            } else {
                model.mergeAttributes(feedback);
            }
        } catch (IOException e) {
            model.addAttribute("photoError", "Problems with saving photo! Try later :(");
        }
        return "edit_profile";
    }
}
