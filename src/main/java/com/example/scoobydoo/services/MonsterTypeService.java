package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.MonsterType;
import com.example.scoobydoo.repos.MonsterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterTypeService {
    @Autowired
    private MonsterTypeRepo monsterTypeRepo;
    public MonsterType getMonsterTypeByName(String name) {
        return monsterTypeRepo.findMonsterTypeByName(name);
    }
}
