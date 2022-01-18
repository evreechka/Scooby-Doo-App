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
        System.out.println(profile.getPhoto());
        if (profile.getPhoto() != null) {
            params.put("photo", profile.getPhoto());

        }
        params.put("name", investigator.getCharacter().getName());
        params.put("surname", investigator.getCharacter().getSurname());
        params.put("age", investigator.getCharacter().getAge());
        params.put("crime_count", investigator.getCrimes().size());
        params.put("feature", investigator.getFeature());
        return params;
    }

    public Profile getProfile(long profileId) {
        return profileRepo.findProfileById(profileId);
    }

    public Map<String, String> saveChanges(long profileId, String username, String password, MultipartFile file) throws IOException {
        Profile profile = profileRepo.findProfileById(profileId);
        Map<String, String> map = new HashMap<>();
        if (file != null &&  !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists())
                    uploadDir.mkdir();
                String uuid = java.util.UUID.randomUUID().toString();
                String resultFileName = uuid + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + System.getProperty("file.separator") + resultFileName));
                profile.setPhoto(resultFileName);
        }
        if (username != null && !username.equals(profile.getUsername())) {
            if (profileRepo.findProfileByUsername(username) == null)
                profile.setUsername(username);
            else
                map.put("usernameError", "Profile with username=" + username + " is already exists");
        }
        if (password != null)
            profile.setPassword(password);
        profileRepo.save(profile);
        return map;
    }
}
