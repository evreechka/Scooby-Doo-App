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
    public CriminalCase getClueCriminalCase(long criminalCaseId) {
        return criminalCaseService.getCriminalCaseById(criminalCaseId);
    }
    public Map<String, String> saveClue(Clue clue, long criminalCaseId, String visitNumberString) {
        Map<String, String> map = new HashMap<>();
        CriminalCase criminalCase = criminalCaseService.getCriminalCaseById(criminalCaseId);
        CrimeVisit crimeVisit = null;
        int visitNumber;
        try {
            visitNumber = Integer.parseInt(visitNumberString);
        } catch (NumberFormatException e) {
            map.put("visitError", "Incorrect format!");
            return map;
        }
        for (CrimeVisit visit: criminalCase.getCrime().getCrimeVisits()) {
            if (visit.getVisitNumber() == visitNumber) {
                crimeVisit = visit;
                break;
            }
        }
        if (crimeVisit == null) {
            map.put("visitError", "Crime visit with number=" + visitNumberString + " doesn't exist");
            return map;
        }
        clue.setCrimeVisit(crimeVisit);
        clue.setCriminalCase(criminalCase);
        clueRepo.save(clue);
        return null;
    }

    public void addSuspects(long clueId, String[] suspectIds) {
        Clue clue = clueRepo.findClueById(clueId);
        for (String suspectId: suspectIds) {
            System.out.println(suspectId);
            clue.getSuspects().add(suspectService.getSuspect(Long.parseLong(suspectId)));
        }
    }
}