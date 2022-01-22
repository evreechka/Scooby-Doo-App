package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.CrimeVisit;
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
    public Map<String, String> addVictim(String indication, String userId, CrimeVisit crimeVisit) {
        Map<String, String> map = new HashMap<>();
        Victim victim = new Victim();
        VictimKey id = new VictimKey();
        if (indication.trim().isEmpty()) {
            map.put("indicationError", "Indication cannot be blank");
            return map;
        }
        Character character = characterService.getCharacter(Long.parseLong(userId));
        id.setVisitId(crimeVisit.getId());
        id.setCharacterId(character.getId());
        victim.setIndication(indication);
        victim.setDateIndication(LocalDateTime.now());
        victim.setCharacter(character);
        victim.setCrimeVisit(crimeVisit);
        victimRepo.save(victim);
        return null;
    }
}
