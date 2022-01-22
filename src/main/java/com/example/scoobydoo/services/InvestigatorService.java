package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Character;
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
    @Autowired
    private CharacterService characterService;
    public List<Investigator> getAllInvestigators() {
        List<Investigator> users = investigatorRepo.findAll();
        users = users.stream().filter(user -> user.getCharacter().getProfile().isInvestigator() ||user.getCharacter().getProfile().isAdmin()).collect(Collectors.toList());
        return users;
    }
    public Investigator getInvestigatorById(long id) {
        return investigatorRepo.findInvestigatorByInvestigatorId(id);
    }

    public void deleteInvestigator(long id) {
        bankAccountService.deleteBankAccount(getInvestigatorById(id).getBankAccount().getId());
        investigatorRepo.delete(id);
        Character character = characterService.getCharacter(id);
        character.getProfile().setRole(SystemRoleType.USER);
        characterService.createCharacter(character);
    }

    public void addFee(Investigator investigator, float fee) {
        bankAccountService.addFee(investigator.getBankAccount(), fee);
    }

    public void addInvestigator(String userId, String feature) {
        Character character = characterService.getCharacter(Long.parseLong(userId));
        character.getProfile().setRole(SystemRoleType.INVESTIGATOR);
        characterService.createCharacter(character);
        Investigator investigator = new Investigator();
        investigator.setInvestigatorId(character.getId());
        investigator.setFeature(FeatureType.valueOf(feature));
        bankAccountService.createAccount(investigator);
    }
}
