package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.VisitParticipant;
import com.example.scoobydoo.entities.embedded_keys.VisitParticipantKey;
import com.example.scoobydoo.entities.enums.VisitRoleType;
import com.example.scoobydoo.repos.CrimeVisitRepo;
import com.example.scoobydoo.repos.VisitParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CrimeVisitService {
    @Autowired
    private CrimeVisitRepo crimeVisitRepo;
    @Autowired
    private CrimeService crimeService;
    @Autowired
    private VisitParticipantRepo visitParticipantRepo;
    public CrimeVisit getCrimeVisit(long crimeVisitId) {
        return crimeVisitRepo.findCrimeVisitById(crimeVisitId);
    }

    public Map<String,String> addCrimeVisit(long crimeId, String severityDestructionString) {
       int severityDestruction;
        try {
            severityDestruction = Integer.parseInt(severityDestructionString);
        } catch (NumberFormatException e) {
            return Map.of("severityDestructionError", "Invalid format!");

        }
        if (severityDestruction < 0 || severityDestruction > 10) {
            return Map.of("severityDestructionError", "Invalid format!");
        }
        List<String> roles = List.of("CLUE_SEARCHER", "VICTIM_INTERVIEW", "CRIME_SCENE_INSPECTOR");
        CrimeVisit crimeVisit = new CrimeVisit();
        Crime crime = crimeService.getCrime(crimeId);
        int visitNumber = 1;
        for(CrimeVisit visit: crimeVisitRepo.findAllByCrime(crime)) {
            if (visit.getVisitNumber() > visitNumber)
                visitNumber = visit.getVisitNumber();
        }
        crimeVisit.setCrime(crime);
        crimeVisit.setCrimeScene(crimeVisit.getCrimeScene());
        crimeVisit.setDateVisit(LocalDateTime.now());
        crimeVisit.setVisitNumber(visitNumber + 1);
        crimeVisitRepo.save(crimeVisit);
        for (Investigator investigator: crime.getInvestigators()) {
            VisitParticipant visitParticipant = new VisitParticipant();
            VisitParticipantKey id = new VisitParticipantKey();
            id.setVisitId(crimeVisit.getId());
            id.setParticipantId(investigator.getInvestigatorId());
            visitParticipant.setId(id);
            visitParticipant.setCrimeVisit(crimeVisit);
            visitParticipant.setInvestigator(investigator);
            visitParticipant.setVisitRole(VisitRoleType.valueOf(roles.get((int) Math.round(Math.random() * 2))));
            visitParticipantRepo.save(visitParticipant);
        }
        return null;
    }
}
