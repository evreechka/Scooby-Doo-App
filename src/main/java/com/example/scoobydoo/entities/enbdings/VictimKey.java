package com.example.scoobydoo.entities.enbdings;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class VictimKey implements Serializable {
    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "character_id")
    private Long characterId;
}
