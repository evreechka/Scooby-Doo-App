package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.Profile;
import com.example.scoobydoo.repos.InventoryRepo;
import com.example.scoobydoo.repos.InvestigatorRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvestigatorService {
    @Autowired
    private InvestigatorRepo investigatorRepo;
    @Autowired
    private ProfileRepo profileRepo;

}
