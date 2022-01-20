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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CrimeService {
    @Autowired
    private CrimeRepo crimeRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private InvestigatorService investigatorService;

    public List<Crime> getAllCrimes(UserDetails profileDetails) {
        List<Crime> crimes = crimeRepo.findAll();
        Profile profile = profileRepo.findProfileByUsername(profileDetails.getUsername());
        if (profile.getUser().getCharacter().getRole().toString().equals(SystemRoleType.SHERIFF.name())) {
            crimes = crimes.stream().filter(crime -> crime.getSheriff().getId() == profile.getUser().getCharacter().getId()).collect(Collectors.toList());
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
        if (crime.getSheriff().getId() != profileRepo.findProfileByUsername(profileDetails.getUsername()).getUser().getInvestigatorId()) {
            return Map.of("error", "Permission denied!");
        }
        for (CriminalCase criminalCase : crime.getCriminalCases()) {
            if (criminalCase.getQuilt() == null || criminalCase.getPunishment() == null)
                return Map.of("error", "Not all criminal cases are closed!");
        }
        Set<Investigator> investigators = crime.getInvestigators();
        float fee = crime.getFee() / investigators.size();
        for (Investigator investigator : investigators) {
            investigatorService.addFee(investigator, fee);
        }
        crime.setCrimeStatus(CrimeStatusType.CLOSED);
        return Map.of("success", "Crime successfully closed!");
    }
}
