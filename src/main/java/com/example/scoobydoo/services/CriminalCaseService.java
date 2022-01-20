package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.entities.enums.PunishmentType;
import com.example.scoobydoo.repos.CriminalCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CriminalCaseService {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private CriminalCaseRepo criminalCaseRepo;
    @Autowired
    private SuspectService suspectService;
    @Autowired
    private CrimeService crimeService;
    @Autowired
    private MonsterService monsterService;
    public CriminalCase getCriminalCaseById(long criminalCaseId) {
        return criminalCaseRepo.findCriminalCasesById(criminalCaseId);
    }
    public Map<String, String> getSeverityGradation(long criminalCaseId) {
        CriminalCase criminalCase = getCriminalCaseById(criminalCaseId);
        if (criminalCase.getSeverity() >= 0 && criminalCase.getSeverity() <= 1) {
            return Map.of("severity", "nothing");
        } else if (criminalCase.getSeverity() >= 2 && criminalCase.getSeverity() <= 4) {
            return Map.of("severity", "small");
        } else if (criminalCase.getSeverity() >= 5 && criminalCase.getSeverity() <= 7) {
            return Map.of("severity", "middle");
        }
        return Map.of("severity", "large");

    }
    public Map<String, Object> closeCriminalCase(long criminalCaseId, UserDetails profileDetails) {
        Profile activeProfile = profileService.getProfileByUsername(profileDetails.getUsername());
        if (!activeProfile.isAdmin()) {
            return Map.of("error", "Permission denied!");
        }
        if (!criminalCaseRepo.isEnoughEvidence(criminalCaseId)) {
            return Map.of("error", "Not enough evidence");
        }
        CriminalCase criminalCase = criminalCaseRepo.findCriminalCasesById(criminalCaseId);
        Set<Suspect> suspects = criminalCase.getAllSuspect();
        for (Clue clue: criminalCase.getClues()) {
            suspects.stream().filter(suspect -> clue.getSuspects().contains(suspect)).forEach(Suspect::incInvolvement);
        }
        int max_involvement = -1;
        Suspect guilt = null;
        for (Suspect suspect: suspects) {
            suspectService.saveInvolvement(suspect);
            if (suspect.getInvolvement() > max_involvement) {
                max_involvement = suspect.getInvolvement();
                guilt = suspect;
            }
        }
        if (criminalCase.getSeverity() >= 0 && criminalCase.getSeverity() <= 1) {
            criminalCase.setPunishment(PunishmentType.NONE);
        } else if (criminalCase.getSeverity() >= 2 && criminalCase.getSeverity() <= 4) {
            criminalCase.setPunishment(PunishmentType.PUBLIC_WORKS);
        } else if (criminalCase.getSeverity() >= 5 && criminalCase.getSeverity() <= 7) {
            criminalCase.setPunishment(PunishmentType.FINE);
        } else {
            criminalCase.setPunishment(PunishmentType.ARREST);
        }
        return Map.of("guilt", guilt);
    }
    public void addCriminalCase(long crimeId, long monsterId, CriminalCase criminalCase) {
        criminalCase.setCrime(crimeService.getCrime(crimeId));
        criminalCase.setMonster(monsterService.getMonster(monsterId));
        criminalCase.setClues(new HashSet<>());
        criminalCase.setEquipments(new HashSet<>());
        criminalCase.setOrders(new HashSet<>());
    }
}
