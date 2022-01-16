package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.VictimKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "VICTIM")
public class Victim implements Serializable {
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
