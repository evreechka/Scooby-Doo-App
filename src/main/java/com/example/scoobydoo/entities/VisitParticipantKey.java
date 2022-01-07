package com.example.scoobydoo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class VisitParticipantKey implements Serializable {
    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "investigator_id")
    private Long investigatorId;
}
