package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Clue")
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clue_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="visit_id")
    private CrimeVisit crimeVisit;

    @ManyToOne
    @JoinColumn(name="case_id")
    private CriminalCase criminalCase;

    @ManyToMany
    @JoinTable(
            name = "Suspect_Clue",
            joinColumns = @JoinColumn(name = "clue_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Suspect> suspects;
}
