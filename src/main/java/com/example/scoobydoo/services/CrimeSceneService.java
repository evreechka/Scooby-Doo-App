package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.CrimeScene;
import com.example.scoobydoo.repos.CrimeSceneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CrimeSceneService {
    @Autowired
    private CrimeSceneRepo crimeSceneRepo;
    public List<CrimeScene> getAllCrimeScene() {
        return crimeSceneRepo.findAll();
    }
    public CrimeScene getCrimeScene(long crimeSceneId) {
        return crimeSceneRepo.findCrimeSceneById(crimeSceneId);
    }
}
