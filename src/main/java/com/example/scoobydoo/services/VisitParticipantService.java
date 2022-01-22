package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CrimeVisit;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.entities.VisitParticipant;
import com.example.scoobydoo.entities.embedded_keys.VisitParticipantKey;
import com.example.scoobydoo.entities.enums.VisitRoleType;
import com.example.scoobydoo.repos.VisitParticipantRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitParticipantService {
    @Autowired
    private VisitParticipantRepo visitParticipantRepo;

    public void setVisitParticipant(Crime crime, CrimeVisit crimeVisit, String[] roles) {
        for (Investigator investigator: crime.getInvestigators()) {
            VisitParticipant visitParticipant = new VisitParticipant();
            VisitParticipantKey id = new VisitParticipantKey();
            id.setVisitId(crimeVisit.getId());
            id.setParticipantId(investigator.getInvestigatorId());
            visitParticipant.setId(id);
            visitParticipant.setCrimeVisit(crimeVisit);
            visitParticipant.setInvestigator(investigator);
            visitParticipant.setVisitRole(VisitRoleType.valueOf(roles[(int) Math.round(Math.random() * 2)]));
            visitParticipantRepo.save(visitParticipant);
        }
    }
}
