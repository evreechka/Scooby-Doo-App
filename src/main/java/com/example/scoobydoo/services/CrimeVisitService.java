package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.repos.CrimeVisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrimeVisitService {
    @Autowired
    private CrimeVisitRepo crimeVisitRepo;
    @Autowired
    private VisitParticipantService visitParticipantService;
    @Autowired
    private CrimeSceneService crimeSceneService;
    public CrimeVisit getCrimeVisit(long crimeVisitId) {
        return crimeVisitRepo.findCrimeVisitById(crimeVisitId);
    }

    public void addCrimeVisit(Crime crime, CrimeVisit crimeVisit, String crimeScene, String[] roles) {
        int visitNumber = getVisitNumber(crime);
        crimeVisit.setCrime(crime);
        crimeVisit.setCrimeScene(crimeSceneService.getCrimeScene(Long.parseLong(crimeScene)));
        crimeVisit.setCrimeScene(crimeVisit.getCrimeScene());
        crimeVisit.setDateVisit(LocalDateTime.now());
        crimeVisit.setVisitNumber(visitNumber + 1);
        crimeVisitRepo.save(crimeVisit);
        visitParticipantService.setVisitParticipant(crime, crimeVisit, roles);
    }

    private int getVisitNumber(Crime crime) {
        int visitNumber = 1;
        for(CrimeVisit visit: crimeVisitRepo.findAllByCrime(crime)) {
            if (visit.getVisitNumber() > visitNumber)
                visitNumber = visit.getVisitNumber();
        }
        return visitNumber;
    }
}
