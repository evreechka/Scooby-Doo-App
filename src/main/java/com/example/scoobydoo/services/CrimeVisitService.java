package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.repos.CrimeVisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrimeVisitService {
    @Autowired
    private CrimeVisitRepo crimeVisitRepo;
    public CrimeVisit getCrimeVisit(long crimeVisitId) {
        return crimeVisitRepo.findCrimeVisitById(crimeVisitId);
    }
}
