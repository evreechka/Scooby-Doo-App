package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Monster;
import com.example.scoobydoo.entities.MonsterType;
import com.example.scoobydoo.repos.MonsterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {
    @Autowired
    private MonsterRepo monsterRepo;
    @Autowired
    private MonsterTypeService monsterTypeService;

    public List<Monster> getAllMonsters() {
        return monsterRepo.findAll();
    }

    public void createMonster(Monster monster, String type) {
        monster.setMonsterType(monsterTypeService.getMonsterTypeByName(type));
        monsterRepo.save(monster);
    }
}
