package com.example.scoobydoo.entities.investigation.crime;

import com.example.scoobydoo.entities.characters.Investigator;
import com.example.scoobydoo.entities.enbdings.VisitParticipantKey;
import com.example.scoobydoo.entities.enums.VisitRoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Visit_Participant")
public class VisitParticipant {
    @EmbeddedId
    private VisitParticipantKey id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "visit_role")
    private VisitRoleType visitRole;

    @ManyToOne
    @MapsId("visitId")
    @JoinColumn(name = "visit_id")
    private CrimeVisit crimeVisit;

    @ManyToOne
    @MapsId("investigatorId")
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;
}
