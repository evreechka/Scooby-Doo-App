package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Clue;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.CriminalCase;
import com.example.scoobydoo.repos.ClueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClueService {
    @Autowired
    private ClueRepo clueRepo;
    @Autowired
    private CriminalCaseService criminalCaseService;
    @Autowired
    private SuspectService suspectService;
    @Autowired
    private CrimeVisitService crimeVisitService;
    public CriminalCase getClueCriminalCase(long criminalCaseId) {
        return criminalCaseService.getCriminalCaseById(criminalCaseId);
    }
    public void saveClue(Clue clue, long criminalCaseId, String visitNumberString) {
        CriminalCase criminalCase = criminalCaseService.getCriminalCaseById(criminalCaseId);
        CrimeVisit crimeVisit = crimeVisitService.getCrimeVisit(Long.parseLong(visitNumberString));
        clue.setCrimeVisit(crimeVisit);
        clue.setCriminalCase(criminalCase);
        clueRepo.save(clue);
    }

    public void addSuspects(long clueId, String[] suspectIds) {
        Clue clue = clueRepo.findClueById(clueId);
        for (String suspectId: suspectIds) {
            System.out.println(suspectId);
            clue.getSuspects().add(suspectService.getSuspect(Long.parseLong(suspectId)));
        }
    }
}
