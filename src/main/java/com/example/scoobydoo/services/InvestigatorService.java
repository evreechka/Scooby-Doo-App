package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.InventoryRepo;
import com.example.scoobydoo.repos.InvestigatorRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvestigatorService {
    @Autowired
    private InvestigatorRepo investigatorRepo;
    @Autowired
    private ProfileRepo profileRepo;

    public List<Investigator> getAllInvestigators() {
        List<Investigator> users = investigatorRepo.findAll();
        users = users.stream().filter(user -> user.getCharacter().getRole().name().equals(SystemRoleType.INVESTIGATOR.name()) ||user.getCharacter().getRole().name().equals(SystemRoleType.ADMIN.name())).collect(Collectors.toList());
        return users;
    }
}
