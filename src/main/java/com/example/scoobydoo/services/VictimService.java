package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Victim;
import com.example.scoobydoo.entities.embedded_keys.VictimKey;
import com.example.scoobydoo.repos.VictimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Service
public class VictimService {
    @Autowired
    private VictimRepo victimRepo;
    @Autowired
    private CharacterService characterService;

    @Autowired
    private CrimeVisitService crimeVisitService;
    public Map<String, String> addVictim(String indication, String characterIdString, long crimeVisitId) {
        Map<String, String> map = new HashMap<>();
        Victim victim = new Victim();
        VictimKey id = new VictimKey();
        if (indication.trim().isEmpty()) {
            map.put("indicationError", "Indication cannot be blank");
            return map;
        }
        long characterId;
        try {
            characterId = Long.parseLong(characterIdString);
        } catch (NumberFormatException e) {
            map.put("idError", "Invalid format of the number!");
            return map;
        }
        Character character = characterService.getCharacter(characterId);
        if (character == null) {
            map.put("idError", "Character with id=" + characterIdString + " doesn't exist!");
            return map;
        }
        id.setVisitId(crimeVisitId);
        id.setCharacterId(characterId);
        victim.setIndication(indication);
        victim.setDateIndication(LocalDateTime.now());
        victim.setCharacter(character);
        victim.setCrimeVisit(crimeVisitService.getCrimeVisit(crimeVisitId));
        victimRepo.save(victim);
        return null;
    }
}
