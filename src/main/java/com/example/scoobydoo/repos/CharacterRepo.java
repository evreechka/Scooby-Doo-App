package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CharacterRepo extends JpaRepository<Character, Long> {
    Character findCharacterById(Long characterId);
    Character findCharacterByNameAndSurnameAndAge(String name, String surname, Integer age);
}
