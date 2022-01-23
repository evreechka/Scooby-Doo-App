package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Address;
import com.example.scoobydoo.entities.CrimeScene;
import com.example.scoobydoo.entities.enums.PlaceType;
import com.example.scoobydoo.services.CrimeSceneService;
import javax.validation.Valid;

import com.example.scoobydoo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
public class CrimeSceneController {

    @Autowired
    private CrimeSceneService crimeSceneService;
    @Autowired
    private ProfileService profileService;
    @GetMapping("/crime_scene/add")
    public String getAddCrimeScenePage() {
        return "add_crime_scene";
    }

    @PostMapping("/crime_scene/add")
    public String addCrimeScene(Model model, @Valid CrimeScene crimeScene, BindingResult crimeSceneBinding, @Valid Address address, BindingResult addressBinding, @AuthenticationPrincipal UserDetails userDetails) {
        if (addressBinding.hasErrors() || crimeSceneBinding.hasErrors()) {
            model.mergeAttributes(getErrors(addressBinding));
            model.mergeAttributes(getErrors(crimeSceneBinding));
        } else {
            crimeSceneService.addCrimeScene(crimeScene, address);
            return "redirect:/profile/" + profileService.getProfileByUsername(userDetails.getUsername()).getId();
        }
        return "add_crime_scene";
    }
}
