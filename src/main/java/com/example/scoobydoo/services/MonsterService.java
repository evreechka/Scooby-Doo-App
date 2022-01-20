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

    public Monster createMonster(Monster monster, String type) {
        monster.setMonsterType(monsterTypeService.getMonsterTypeByName(type));
        return monsterRepo.save(monster);
    }
    public Monster getMonster(long monsterId) {
        return monsterRepo.findMonsterById(monsterId);
    }
}
