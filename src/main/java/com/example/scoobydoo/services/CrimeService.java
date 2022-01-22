package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.enums.CrimeStatusType;
import com.example.scoobydoo.repos.CrimeRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrimeService {
    @Autowired
    private CrimeRepo crimeRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private InvestigatorService investigatorService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private ContentionService contentionService;
    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CriminalCaseService criminalCaseService;
    @Autowired
    private CrimeVisitService crimeVisitService;
    @Autowired
    private CrimeSceneService crimeSceneService;
    public List<Crime> getAllCrimes(UserDetails profileDetails) {
        List<Crime> crimes = crimeRepo.findAll();
        Profile profile = profileRepo.findProfileByUsername(profileDetails.getUsername());
        if (profile.isSheriff()) {
            crimes = crimes.stream().filter(crime -> crime.getSheriff().getId() == profile.getUser().getId()).collect(Collectors.toList());
        }
        crimes.sort(Comparator.comparing(Crime::getCrimeStatus));
        return crimes;
    }

    public Crime getCrime(long crimeId) {
        return crimeRepo.findCrimeById(crimeId);
    }

    public String getDamage(long crimeId) {
        int damage = getCrime(crimeId).getContention().getDamageCritically();
        if (damage >= 0 && damage <= 4)
            return "small";
        else if (damage >= 5 && damage <= 7)
            return "middle";
        return "large";
    }

    public Set<Address> getVictimHomes(long crimeId) {
        Character character = crimeRepo.findCrimeById(crimeId).getContention().getCharacter();
        return character.getLivingPlaces();
    }

    public Map<String, String> closeCrime(long crimeId, UserDetails profileDetails) {
        Crime crime = crimeRepo.findCrimeById(crimeId);
        Map<String, String> map = new HashMap<>();
        if (crime.getSheriff().getId() != profileRepo.findProfileByUsername(profileDetails.getUsername()).getUser().getId()) {
            map.put("error", "Permission denied!");
            return map;
        }
        for (CriminalCase criminalCase : crime.getCriminalCases()) {
            if (criminalCase.getQuilt() == null || criminalCase.getPunishment() == null) {
                map.put("error", "Not all criminal cases are closed!");
                return map;
            }
        }
        Set<Investigator> investigators = crime.getInvestigators();
        float fee = crime.getFee() / investigators.size();
        for (Investigator investigator : investigators) {
            investigatorService.addFee(investigator, fee);
        }
        crime.setCrimeStatus(CrimeStatusType.CLOSED);
        map.put("success", "Crime successfully closed!");
        return map;
    }
    public List<Investigator> getInvestigators() {
        return investigatorService.getAllInvestigators();
    }
    //contention
    //crime visit
    //criminalCase
    public void createCrime(Crime crime, Character victim, Contention contention, Monster monster, CriminalCase criminalCase, CrimeVisit crimeVisit, String sheriffId, String crimeSceneId, String[] invIds, String type) {
        characterService.createCharacter(victim);
        contention.setDateContention(LocalDateTime.now());
        contention.setCharacter(victim);
        monsterService.createMonster(monster, type);
        contentionService.createContention(contention);
        criminalCase.setMonster(monster);
        criminalCaseService.createCriminalCase(criminalCase);
        crime.setInvestigators(new HashSet<>());
        crime.setContention(contention);
        crime.setCrimeVisits(new HashSet<>());
        crime.setCriminalCases(new HashSet<>());
        crime.setSheriff(characterService.getCharacter(Long.parseLong(sheriffId)));
        crime.setCrimeStatus(CrimeStatusType.ACTIVE);
        for (String inv: invIds) {
            crime.getInvestigators().add(investigatorService.getInvestigatorById(Long.parseLong(inv)));
        }
        crime.getCriminalCases().add(criminalCase);
        crimeRepo.save(crime);
        String[] roles = new String[]{"CLUE_SEARCHER", "VICTIM_INTERVIEW", "CRIME_SCENE_INSPECTOR"};
        crimeVisitService.addCrimeVisit(crime, crimeVisit, crimeSceneId, roles);
        crime.getCrimeVisits().add(crimeVisit);
        crimeRepo.save(crime);
    }
}
