package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.VisitParticipantKey;
import com.example.scoobydoo.entities.enums.VisitRoleType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "VISIT_PARTICIPANT")
public class VisitParticipant implements Serializable {
    @EmbeddedId
    private VisitParticipantKey id;

    @Enumerated(EnumType.STRING)
    @Column(name = "visit_role")
    private VisitRoleType visitRole;

    //TODO
    @ManyToOne
    @MapsId("visitId")
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id")
    private CrimeVisit crimeVisit;

    //TODO
    @ManyToOne
    @MapsId("participantId")
    @JoinColumn(name = "participant_id", referencedColumnName = "investigator_id")
    private Investigator investigator;
}
