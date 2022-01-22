package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.enums.FeatureType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import com.example.scoobydoo.repos.InvestigatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestigatorService {
    @Autowired
    private InvestigatorRepo investigatorRepo;
    @Autowired
    private BankAccountService bankAccountService;
    public List<Investigator> getAllInvestigators() {
        List<Investigator> users = investigatorRepo.findAll();
        users = users.stream().filter(user -> user.getCharacter().getProfile().isInvestigator() ||user.getCharacter().getProfile().isAdmin()).collect(Collectors.toList());
        return users;
    }
    public Investigator createInvestigator(long id, String feature) {
        Investigator newInvestigator = new Investigator();
        newInvestigator.setInvestigatorId(id);
        newInvestigator.setFeature(FeatureType.valueOf(feature));
        bankAccountService.createAccount(newInvestigator);
        return newInvestigator;
    }
    public Investigator getInvestigatorById(long id) {
        return investigatorRepo.findInvestigatorByInvestigatorId(id);
    }
    public void deleteInvestigator(long id) {
//        Investigator investigator = getInvestigatorById(id);
        bankAccountService.deleteBankAccount(getInvestigatorById(id).getBankAccount().getId());
        investigatorRepo.delete(id);
    }

    public void addFee(Investigator investigator, float fee) {
        bankAccountService.addFee(investigator.getBankAccount(), fee);
    }
}
