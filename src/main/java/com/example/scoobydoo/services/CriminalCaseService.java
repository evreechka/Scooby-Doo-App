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
        Map<String, String> map = new HashMap<>();
        CriminalCase criminalCase = getCriminalCaseById(criminalCaseId);
        if (criminalCase.getSeverity() >= 0 && criminalCase.getSeverity() <= 1) {
            map.put("severity", "nothing");
            return map;
        } else if (criminalCase.getSeverity() >= 2 && criminalCase.getSeverity() <= 4) {
            map.put("severity", "small");
            return map;
        } else if (criminalCase.getSeverity() >= 5 && criminalCase.getSeverity() <= 7) {
            map.put("severity", "middle");
            return map;
        }
        map.put("severity", "large");
        return map;

    }
    public Map<String, Object> closeCriminalCase(long criminalCaseId, UserDetails profileDetails) {
        Profile activeProfile = profileService.getProfileByUsername(profileDetails.getUsername());
        Map<String, Object> map = new HashMap<>();
        if (!activeProfile.isAdmin()) {
            map.put("error", "Permission denied!");
            return map;
        }
        if (!criminalCaseRepo.isEnoughEvidence(criminalCaseId)) {
            map.put("error", "Not enough evidence");
            return map;
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
        map.put("guilt", guilt);
        return map;
    }
    public void addCriminalCase(long crimeId, long monsterId, CriminalCase criminalCase) {
        criminalCase.setCrime(crimeService.getCrime(crimeId));
        criminalCase.setMonster(monsterService.getMonster(monsterId));
        criminalCase.setClues(new HashSet<>());
        criminalCase.setEquipments(new HashSet<>());
        criminalCase.setOrders(new HashSet<>());
    }
}
