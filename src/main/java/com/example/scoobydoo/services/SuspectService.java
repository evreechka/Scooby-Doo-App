package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.Suspect;
import com.example.scoobydoo.repos.SuspectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SuspectService {
    @Autowired
    private SuspectRepo suspectRepo;
    @Autowired
    private CrimeVisitService crimeVisitService;
    @Autowired
    private CharacterService characterService;
    public void saveInvolvement(Suspect suspect) {
        suspectRepo.save(suspect);
    }

    public Map<String, String> addSuspect(long crimeVisitId, Suspect suspect, String characterIdString) {
        CrimeVisit crimeVisit = crimeVisitService.getCrimeVisit(crimeVisitId);
        long characterId;
        try {
            characterId = Long.parseLong(characterIdString);
        } catch (NumberFormatException e) {
            return Map.of("idError", "Invalid format of the number!");
        }
        Character character = characterService.getCharacter(characterId);
        if (character == null)
            return Map.of("idError", "Character with id=" + characterIdString + " doesn't exist!");
        suspect.setCharacterId(character);
        suspect.setCrimeVisit(crimeVisit);
        suspectRepo.save(suspect);
        return null;
    }
}
