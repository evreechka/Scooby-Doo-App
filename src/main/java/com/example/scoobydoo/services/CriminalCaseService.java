package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.entities.enums.PunishmentType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.entities.enums.VisitRoleType;
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
    private MonsterService monsterService;
    @Autowired
    private CrimeVisitService crimeVisitService;


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
        if (!activeProfile.isInvestigator() && !activeProfile.isAdmin()) {
            map.put("error", "Permission denied!");
            return map;
        }
        if (!criminalCaseRepo.isEnoughEvidence(criminalCaseId)) {
            map.put("error", "Not enough evidence");
            return map;
        }
        CriminalCase criminalCase = criminalCaseRepo.findCriminalCasesById(criminalCaseId);
        if(criminalCase.getTrapCases().isEmpty()) {
            map.put("error", "You haven't create any traps");
            return map;
        }
        Set<Suspect> suspects = criminalCase.getAllSuspect();
        if (suspects.isEmpty()) {
            map.put("error", "You haven't any suspects");
            return map;
        }
        for (Clue clue : criminalCase.getClues()) {
            suspects.stream().filter(suspect -> clue.getSuspects().contains(suspect)).forEach(Suspect::incInvolvement);
        }
        String[] arr = {VisitRoleType.BAIT.toString(), VisitRoleType.TRAP_MANAGER.toString(), VisitRoleType.BOBBY_TRAPS.toString()};
        List<CrimeScene> crimeScenes = criminalCase.getAllCrimeScenes();
        crimeVisitService.addCrimeVisit(criminalCase.getCrime(), new CrimeVisit(), Long.toString(crimeScenes.get((int) Math.round(Math.random() * (crimeScenes.size() - 1))).getId()), arr);
        int max_involvement = -1;
        Suspect guilt = null;
        for (Suspect suspect : suspects) {
            suspectService.saveInvolvement(suspect);
            if (suspect.getInvolvement() > max_involvement) {
                max_involvement = suspect.getInvolvement();
                guilt = suspect;
            }
        }
        criminalCase.setQuilt(guilt);
        if (criminalCase.getSeverity() >= 0 && criminalCase.getSeverity() <= 1) {
            criminalCase.setPunishment(PunishmentType.NONE);
        } else if (criminalCase.getSeverity() >= 2 && criminalCase.getSeverity() <= 4) {
            criminalCase.setPunishment(PunishmentType.PUBLIC_WORKS);
        } else if (criminalCase.getSeverity() >= 5 && criminalCase.getSeverity() <= 7) {
            criminalCase.setPunishment(PunishmentType.FINE);
        } else {
            criminalCase.setPunishment(PunishmentType.ARREST);
        }
        criminalCaseRepo.save(criminalCase);
        return map;
    }

    public void addCriminalCase(Crime crime, Monster monster, String type, CriminalCase criminalCase) {
        monsterService.createMonster(monster, type);
        criminalCase.setCrime(crime);
        criminalCase.setMonster(monster);
        criminalCase.setClues(new HashSet<>());
        criminalCase.setEquipments(new HashSet<>());
        criminalCase.setOrders(new HashSet<>());
        createCriminalCase(criminalCase);
    }

    public void createCriminalCase(CriminalCase criminalCase) {
        criminalCaseRepo.save(criminalCase);
    }
}
