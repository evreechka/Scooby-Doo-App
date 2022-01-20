package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Contention;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.repos.ContentionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        try {
            damageCritically = Integer.parseInt(damageCriticallyString);
        } catch (NumberFormatException e) {
            return Map.of("damageCriticallyError", "Invalid format!");
        }
        if (damageCritically < 0 || damageCritically > 10) {
            return Map.of("damageCriticallyError", "Should be between 0 and 10!");
        }
        long characterId;
        try {
            characterId = Long.parseLong(characterIdString);
        } catch (NumberFormatException e) {
            return Map.of("idError", "Invalid format!");
        }
        if (characterService.getCharacter(characterId) == null) {
            return Map.of("idError", "Character with id=" + characterIdString + " doesn't exist!");
        }
        Contention contention = new Contention();
        contention.setDateContention(LocalDateTime.now());
        contention.setCharacter(characterService.getCharacter(characterId));
        contention.setDescription(description);
        contention.setDamageCritically(damageCritically);
        return Map.of("success", contentionRepo.save(contention));
    }
    public Contention getContention(long contentionId) {
        return contentionRepo.findContentionById(contentionId);
    }
}
