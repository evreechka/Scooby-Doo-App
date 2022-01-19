package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.BankAccountRepo;
import com.example.scoobydoo.repos.CharacterRepo;
import com.example.scoobydoo.repos.InvestigatorRepo;
import com.example.scoobydoo.repos.ProfileRepo;
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
        return params;
    }

    public Profile getProfile(long profileId) {
        return profileRepo.findProfileById(profileId);
    }

    public Map<String, String> saveChanges(long profileId, String username, String password, String stringAge, MultipartFile file, UserDetails profileDetails) throws IOException {
        if (profileId != profileRepo.findProfileByUsername(profileDetails.getUsername()).getId())
            return Map.of("error", "Permission denied!");
        Map<String, String> map = new HashMap<>();
        Profile profile = profileRepo.findProfileById(profileId);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            String uuid = java.util.UUID.randomUUID().toString();
            String resultFileName = uuid + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + System.getProperty("file.separator") + resultFileName));
            profile.setPhoto(resultFileName);
        }
        if (!username.isBlank() && !username.equals(profile.getUsername())) {
            if (profileRepo.findProfileByUsername(username) == null)
                profile.setUsername(username);
            else
                map.put("usernameError", "Profile with username=" + username + " is already exists");
        }
        if (!password.isBlank())
            profile.setPassword(password);
        if (!stringAge.isBlank()) {
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
        Profile activeProfile = profileRepo.findProfileByUsername(profileDetails.getUsername());
        Profile deletedProfile = profileRepo.findProfileById(profileId);
        if (!activeProfile.getUser().getCharacter().getRole().name().equals(SystemRoleType.ADMIN.name()))
            return Map.of("error", "Permission denied!");
        if (activeProfile.getId() == deletedProfile.getId()) {
            profileRepo.delete(deletedProfile);
            return Map.of("logout", "logout");
        }
        profileRepo.delete(deletedProfile);
        return null;
    }
}
