package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.enums.SexType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepo characterRepo;
    public List<Character> getAllCharacters() {
        return characterRepo.findAll();
    }
    public Character getCharacter(long characterId) {
        return characterRepo.findCharacterById(characterId);
    }
    public void createCharacter(Character character) {
        characterRepo.save(character);
    }
}
