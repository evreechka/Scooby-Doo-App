package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Suspect")
public class Suspect {
    @EmbeddedId
    private SuspectKey id;

    @NotBlank
    @Column(name = "motive")
    private String motive;

    @Min(0)
    @Column(name = "involvement")
    private int involvement;

    @ManyToOne
    @MapsId("visitId")
    @JoinColumn(name = "visit_id")
    private CrimeVisit crimeVisit;

    @ManyToOne
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    private Character characterId;

    @ManyToMany(mappedBy = "suspects")
    private Set<Clue> clues;
}
