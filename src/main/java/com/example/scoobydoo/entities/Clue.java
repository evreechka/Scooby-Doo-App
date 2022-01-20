package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CLUE")
public class Clue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clue_id")
    private long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "description cannot be blank")
    @Column(name = "description")
    private String description;

    //TODO
    @ManyToOne
    @JoinColumn(name="visit_id", referencedColumnName = "visit_id")
    private CrimeVisit crimeVisit;

    //TODO
    @ManyToOne
    @JoinColumn(name="case_id", referencedColumnName = "case_id")
    private CriminalCase criminalCase;

    //TODO
    @ManyToMany
    @JoinTable(
            name = "SUSPECT_CLUE",
            joinColumns = @JoinColumn(name = "clue_id", referencedColumnName = "clue_id"),
            inverseJoinColumns = @JoinColumn(name = "suspect_id", referencedColumnName = "suspect_id"))
    private Set<Suspect> suspects = new HashSet<>();
}
