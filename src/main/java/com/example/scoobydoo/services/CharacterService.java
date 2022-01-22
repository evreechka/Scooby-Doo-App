package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.repos.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Character createCharacter(Character character) {
        return characterRepo.save(character);
    }
    public Character findCharacterAttributes(String name, String surname, Integer age
    ) {
        return characterRepo.findCharacterByNameAndSurnameAndAge(name, surname, age);
    }

}
