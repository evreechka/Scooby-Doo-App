package com.example.scoobydoo.entities.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class VictimKey implements Serializable {
    @Column(name = "visit_id")
    private long visitId;

    @Column(name = "character_id")
    private long characterId;
}
