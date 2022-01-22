package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Suspect")
public class Suspect implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suspect_id")
    private long id;

    @NotBlank(message = "Motive cannot be blank")
    @Column(name = "motive")
    private String motive;

    @Min(0)
    @Column(name = "involvement")
    private int involvement = 0;

    public void incInvolvement() {
        involvement++;
    }
    //TODO
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id")
    private CrimeVisit crimeVisit;

    //TODO
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "character_id")
    private Character character;

    //TODO
    @ManyToMany(mappedBy = "suspects")
    private Set<Clue> clues;
}
