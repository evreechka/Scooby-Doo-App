package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Contention;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.repos.ContentionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentionService {
    @Autowired
    private ContentionRepo contentionRepo;
    @Autowired
    private CharacterService characterService;
    public Map<String, Object> createContention(String description, String damageCriticallyString, String characterIdString) {
        int damageCritically;
        Map<String, Object> map = new HashMap<>();
        try {
            damageCritically = Integer.parseInt(damageCriticallyString);
        } catch (NumberFormatException e) {
            map.put("damageCriticallyError", "Invalid format!");
            return map;
        }
        if (damageCritically < 0 || damageCritically > 10) {
            map.put("damageCriticallyError", "Should be between 0 and 10!");
            return map;
        }
        long characterId;
        try {
            characterId = Long.parseLong(characterIdString);
        } catch (NumberFormatException e) {
            map.put("idError", "Invalid format!");
            return map;
        }
        if (characterService.getCharacter(characterId) == null) {
            map.put("idError", "Character with id=" + characterIdString + " doesn't exist!");
            return map;
        }
        Contention contention = new Contention();
        contention.setDateContention(LocalDateTime.now());
        contention.setCharacter(characterService.getCharacter(characterId));
        contention.setDescription(description);
        contention.setDamageCritically(damageCritically);
        map.put("success", contentionRepo.save(contention));
        return map;
    }
    public Contention getContention(long contentionId) {
        return contentionRepo.findContentionById(contentionId);
    }
}
