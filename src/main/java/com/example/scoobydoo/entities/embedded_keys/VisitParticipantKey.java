package com.example.scoobydoo.entities.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class VisitParticipantKey implements Serializable {
    @Column(name = "visit_id")
    private long visitId;

    @Column(name = "participant_id")
    private long participantId;
}
