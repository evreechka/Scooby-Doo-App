package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.enums.CrimeStatusType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.CrimeRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.AssociationOverride;
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

    public Map<String, String> createCrime(long contentionId, String description, String feeString, String sheriffIdString, String[] invIds) {
        float fee;
        Map<String, String> map = null;
        try {
            fee = Float.parseFloat(feeString);
        } catch (NumberFormatException e) {
            map = new HashMap<>();
            map.put("feeError", "Invalid format");
            return map;
        }
        if (fee <= 0) {
            map = new HashMap<>();
            map.put("feeError", "Should be > 0.0");
            return map;
        }
        long sheriffId;
        try {
            sheriffId = Long.parseLong(sheriffIdString);
        } catch (NumberFormatException e) {
            map = new HashMap<>();
            map.put("idError", "Invalid format!");
            return map;
        }
        Character sheriff = characterService.getCharacter(sheriffId);
        if (sheriff == null) {
            map = new HashMap<>();
            map.put("idError", "Character with id=" + sheriffIdString + " doesn't exist!");
            return map;
        }
        if (!sheriff.getProfile().isSheriff()) {
            map = new HashMap<>();
            map.put("idError", "Character with id=" + sheriffIdString + " isn't a sheriff!");
            return map;
        }
        Crime crime = new Crime();
        crime.setCrimeVisits(new HashSet<>());
        crime.setCriminalCases(new HashSet<>());
        crime.setDescription(description);
        crime.setCrimeStatus(CrimeStatusType.ACTIVE);
        crime.setContention(contentionService.getContention(contentionId));
        crime.setFee(fee);
        crime.setSheriff(sheriff);
        crime.setInvestigators(new HashSet<>());
        for (String inv: invIds) {
            crime.getInvestigators().add(investigatorService.getInvestigatorById(Long.parseLong(inv)));
        }
        crimeRepo.save(crime);
        return null;
    }
}
