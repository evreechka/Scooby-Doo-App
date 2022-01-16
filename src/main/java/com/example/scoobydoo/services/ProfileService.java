package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.repos.InvestigatorRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileService implements UserDetailsService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepo.findProfileByUsername(username);
    }

//    public Map<String, Object> authenticateUser(String username, String password) {
//        Profile profile = profileRepo.findProfileByUsername(username);
//        if (profile == null)
//            return Map.of("usernameError","Incorrect username");
//        if (!passwordEncoder.encode(password).equals(profile.getPassword())) {
//            return Map.of("passwordError","Incorrect password");
//        }
//        return Map.of("profile", profile);
//    }
    public Map<String, Object> getInvestigatorProfile(long profileId) {
        Profile profile = profileRepo.findProfileById(profileId);
        Investigator investigator = profile.getUser();
        Map<String, Object> params = new HashMap<>();
        params.put("username", profile.getUsername());
        params.put("name", investigator.getCharacter().getName());
        params.put("surname", investigator.getCharacter().getSurname());
        params.put("age", investigator.getCharacter().getAge());
        params.put("crime_count", investigator.getCrimes().size());
        params.put("feature", investigator.getFeature());
        return params;
    }
}
