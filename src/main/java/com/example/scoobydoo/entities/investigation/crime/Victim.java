package com.example.scoobydoo.entities.investigation.crime;

import com.example.scoobydoo.entities.characters.Character;
import com.example.scoobydoo.entities.enbdings.VictimKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Victim")
public class Victim {
    @EmbeddedId
    private VictimKey id;

    @ManyToOne
    @MapsId("visitId")
    @JoinColumn(name = "visit_id")
    private CrimeVisit crimeVisit;

    @ManyToOne
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    private Character character;

    @NotBlank
    @Column(name = "indication")
    private String indication;

    @NotNull
    @Column(name = "date_indication")
    private LocalDateTime dateIndication;

}
