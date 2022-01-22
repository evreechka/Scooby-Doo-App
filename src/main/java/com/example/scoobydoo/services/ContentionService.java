package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Contention;
import com.example.scoobydoo.repos.ContentionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContentionService {
    @Autowired
    private ContentionRepo contentionRepo;
    @Autowired
    private CharacterService characterService;
    public void createContention(Contention contention) {
        contentionRepo.save(contention);
    }
    public Contention getContention(long contentionId) {
        return contentionRepo.findContentionById(contentionId);
    }
}
