package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.BankAccount;
import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.entities.enums.FeatureType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.BankAccountRepo;
import com.example.scoobydoo.repos.CharacterRepo;
import com.example.scoobydoo.repos.InvestigatorRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import com.example.scoobydoo.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private CharacterRepo characterRepo;
    @Autowired
    private InvestigatorService investigatorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepo.findProfileByUsername(username);
    }

    public Map<String, Object> getInvestigatorProfile(long profileId) {
        Profile profile = profileRepo.findProfileById(profileId);
        Investigator investigator = profile.getUser();
        Map<String, Object> params = new HashMap<>();
        System.out.println(profile.getPhoto());
        if (profile.getPhoto() != null) {
            params.put("photo", profile.getPhoto());

        }
        params.put("profileId", profileId);
        params.put("name", investigator.getCharacter().getName());
        params.put("surname", investigator.getCharacter().getSurname());
        params.put("age", investigator.getCharacter().getAge());
        params.put("crime_count", investigator.getCrimes().size());
        params.put("feature", investigator.getFeature());
        params.put("bank_account", investigator.getBankAccount().getBalance());
        params.put("inv_id", investigator.getInvestigatorId());
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
            profile.setPassword(password);
        if (!stringAge.trim().isEmpty()) {
            Character character = profileRepo.findProfileByUsername(username).getUser().getCharacter();
            try {
                int age = Integer.parseInt(stringAge);
                if (age <= 0 || age > 120) {
                    map.put("ageError", "Age can be between 1 and 120");
                } else {
                    character.setAge(age);
                    characterRepo.save(character);
                }
            } catch (NumberFormatException e) {
                map.put("ageError", "Invalid format");
            }
        }

        profileRepo.save(profile);
        return map;
    }

    public Map<String, String> deleteProfile(long profileId, UserDetails profileDetails) {
        Map<String, String> map = new HashMap<>();
        Profile activeProfile = profileRepo.findProfileByUsername(profileDetails.getUsername());
        Profile deletedProfile = profileRepo.findProfileById(profileId);
        if (!activeProfile.getUser().getCharacter().getRole().name().equals(SystemRoleType.ADMIN.name())) {
            map.put("error", "Permission denied!");
            return map;
        }
        if (activeProfile.getId() == deletedProfile.getId()) {
            profileRepo.delete(deletedProfile);
            map.put("logout", "logout");
            return map;
        }
        profileRepo.delete(deletedProfile);
        investigatorService.deleteInvestigator(deletedProfile.getUser().getInvestigatorId());
        Character character = deletedProfile.getUser().getCharacter();
        character.setRole(SystemRoleType.USER);
        characterRepo.save(character);
        return null;
    }

    public Profile getProfileByUsername(String username) {
        return profileRepo.findProfileByUsername(username);
    }

    public Map<String, String> createProfile(UserDetails profileDetails, Profile profile, String feature, String characterIdString, MultipartFile file) {
        Map<String, String> map = new HashMap<>();
        if (!profileRepo.findProfileByUsername(profileDetails.getUsername()).isAdmin()) {
            map.put("error", "Permission denied!");
            return map;
        }
        if (profileRepo.findProfileByUsername(profile.getUsername()) != null) {
            map.put("usernameError", "Profile with username=" + profile.getUsername() + " is already exists!");
            return map;
        }
        long characterId;
        try {
            characterId = Long.parseLong(characterIdString);
        } catch (NumberFormatException e) {
            map.put("idError", "Invalid format of the number!");
            return map;
        }
        if (characterRepo.findCharacterById(characterId) == null) {
            map.put("idError", "Character with id=" + characterIdString + " doesn't exist!");
            return map;
        }
        if (investigatorService.getInvestigatorById(characterId) != null) {
            map.put("idError", "User with id=" + characterIdString + " is already exists!");
            return map;
        }
        if (!ControllerUtils.savePhoto(file, uploadPath, profile)) {
            map.put("photoError", "Some troubles to upload photo :(");
            return map;
        }
        Character character = characterRepo.findCharacterById(characterId);
        character.setRole(SystemRoleType.INVESTIGATOR);
        characterRepo.save(character);
        Investigator newInvestigator = investigatorService.createInvestigator(characterId, feature);
        profile.setUser(newInvestigator);
        profileRepo.save(profile);
        return null;
    }
}
