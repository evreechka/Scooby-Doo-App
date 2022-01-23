package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.Suspect;
import com.example.scoobydoo.repos.SuspectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SuspectService {
    @Autowired
    private SuspectRepo suspectRepo;
    @Autowired
    private CharacterService characterService;
    public void saveInvolvement(Suspect suspect) {
        suspectRepo.save(suspect);
    }

    public void addSuspect(CrimeVisit crimeVisit, Suspect suspect, Character character) {
        characterService.createCharacter(character);
        suspect.setCharacter(character);
        suspect.setCrimeVisit(crimeVisit);
        suspectRepo.save(suspect);
    }
    public Suspect getSuspect(long suspectId) {
        return suspectRepo.findSuspectById(suspectId);
    }
}
