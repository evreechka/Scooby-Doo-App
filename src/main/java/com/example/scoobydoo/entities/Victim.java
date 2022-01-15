package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.VictimKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "VICTIM")
public class Victim {
    @EmbeddedId
    private VictimKey id;

    //TODO
    @ManyToOne
    @MapsId("visitId")
    @JoinColumn(name = "visit_id")
    private CrimeVisit crimeVisit;

    //TODO
    @ManyToOne
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    private Character character;

    @NotNull
    @Column(name = "indication")
    private String indication;

    @NotNull
    @Column(name = "date_indication")
    private LocalDateTime dateIndication;

}
