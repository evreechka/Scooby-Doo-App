package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.CrimeRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrimeService {
    @Autowired
    private CrimeRepo crimeRepo;
    @Autowired
    private ProfileRepo profileRepo;
    public List<Crime> getAllCrimes(UserDetails profileDetails) {
        List<Crime> crimes = crimeRepo.findAll();
        Profile profile = profileRepo.findProfileByUsername(profileDetails.getUsername());
        if (profile.getUser().getCharacter().getRole().toString().equals(SystemRoleType.SHERIFF.name())) {
            crimes = crimes.stream().filter(crime -> crime.getSheriff().getId() == profile.getUser().getCharacter().getId()).collect(Collectors.toList());
        }
        crimes.sort(Comparator.comparing(Crime::getCrimeStatus));
        return crimes;
    }
}
