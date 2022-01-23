package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.ProfileRepo;
import com.example.scoobydoo.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileService implements UserDetailsService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private String uploadPath;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepo.findProfileByUsername(username);
    }

    public Map<String, Object> getInvestigatorProfile(long profileId) {
        Profile profile = profileRepo.findProfileById(profileId);
        Map<String, Object> params = new HashMap<>();
        Investigator investigator = profile.getUser().getInvestigator();
        if (profile.getPhoto() != null) {
            params.put("photo", profile.getPhoto());
        }

        params.put("profileId", profileId);
        params.put("name", profile.getUser().getName());
        params.put("surname", profile.getUser().getSurname());
        params.put("age", profile.getUser().getAge());
        if (investigator != null) {
            params.put("crime_count", investigator.getCrimes().size());
            params.put("feature", investigator.getFeature());
            params.put("bank_account", investigator.getBankAccount().getBalance());
        }
        params.put("inv_id", profile.getUser().getId());
        return params;
    }

    public Profile getProfileById(long profileId) {
        return profileRepo.findProfileById(profileId);
    }

    public Map<String, String> saveChanges(long profileId, String username, String password, String stringAge, MultipartFile file, UserDetails profileDetails) throws IOException {
        Map<String, String> map = new HashMap<>();
        if (profileId != profileRepo.findProfileByUsername(profileDetails.getUsername()).getId()) {
            map.put("error", "Permission denied!");
            return map;
        }
        Profile profile = profileRepo.findProfileById(profileId);
        if (!ControllerUtils.savePhoto(file, uploadPath, profile))
            map.put("photoError", "Some troubles to upload photo :(");
        if (!username.trim().isEmpty() && !username.equals(profile.getUsername())) {
            if (profileRepo.findProfileByUsername(username) == null)
                profile.setUsername(username);
            else
                map.put("usernameError", "Profile with username=" + username + " is already exists");
        }
        if (!password.trim().isEmpty())
            profile.setPassword(passwordEncoder.encode(password));
        if (!stringAge.trim().isEmpty()) {
            Character character = profileRepo.findProfileByUsername(username).getUser();
            try {
                int age = Integer.parseInt(stringAge);
                if (age <= 0 || age > 120) {
                    map.put("ageError", "Age can be between 1 and 120");
                } else {
                    character.setAge(age);
                    characterService.createCharacter(character);
                }
            } catch (NumberFormatException e) {
                map.put("ageError", "Invalid format");
            }
        }

        profileRepo.save(profile);
        return map;
    }

    public Profile getProfileByUsername(String username) {
        return profileRepo.findProfileByUsername(username);
    }

    public void createProfile(Profile profile, Character character) {
        Character existingCharacter = characterService.findCharacterAttributes(character.getName(), character.getSurname(), character.getAge());
        if (existingCharacter == null) {
            existingCharacter = characterService.createCharacter(character);
        }
        if (existingCharacter.getProfile() == null) {
            profile.setUser(existingCharacter);
            profile.setPassword(passwordEncoder.encode(profile.getPassword()));
            profileRepo.save(profile);
        } else {
            existingCharacter.getProfile().setUsername(profile.getUsername());
            existingCharacter.getProfile().setPassword(passwordEncoder.encode(profile.getPassword()));
            existingCharacter.getProfile().setRole(profile.getRole());
            profileRepo.save(existingCharacter.getProfile());
        }

    }
}
