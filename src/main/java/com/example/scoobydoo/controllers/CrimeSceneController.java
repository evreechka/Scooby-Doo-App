package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.Address;
import com.example.scoobydoo.entities.CrimeScene;
import com.example.scoobydoo.entities.enums.PlaceType;
import com.example.scoobydoo.services.CrimeSceneService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.scoobydoo.utils.ControllerUtils.getErrors;

@Controller
public class CrimeSceneController {

    @Autowired
    CrimeSceneService crimeSceneService;

    @PostMapping("/crime_scene/add")
    public String addCrimeScene(Model model, @Valid @RequestParam CrimeScene crimeScene, @Valid @RequestParam Address address, BindingResult crimeSceneBinding, BindingResult addressBinding) {
        if (addressBinding.hasErrors() || crimeSceneBinding.hasErrors()) {
            model.mergeAttributes(getErrors(addressBinding));
            model.mergeAttributes(getErrors(crimeSceneBinding));
        }
        crimeSceneService.addCrimeScene(crimeScene, address);
        return "add_crime_scene";
    }
}
