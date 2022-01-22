package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.repos.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Character> getSheriffs() {
        List<Character> sheriffs = getAllCharacters();
        return sheriffs.stream().filter(character -> character.getProfile()!= null && character.getProfile().isSheriff()).collect(Collectors.toList());
    }
    public List<Character> getUsers() {
        List<Character> users = getAllCharacters();
        return users.stream().filter(user -> user.getProfile() != null && user.getProfile().isUser()).collect(Collectors.toList());
    }

}
