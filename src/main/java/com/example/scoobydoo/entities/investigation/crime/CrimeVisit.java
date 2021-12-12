package com.example.scoobydoo.entities.investigation.crime;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Crime_Visit")
public class CrimeVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id")
    private long id;

    @Min(1)
    @Column(name = "visit_num")
    private int visitNumber;

    @Min(0)
    @Max(10)
    @Column(name = "severity_destruction")
    private int severityDestruction;

    @NotNull
    @Column(name = "date_visit")
    private LocalDateTime dateVisit;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<Victim> victims;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<Suspect> suspects;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<VisitParticipant> participants;

    @ManyToOne
    @JoinColumn(name="scene_id")
    private CrimeScene crimeScene;

    @ManyToOne
    @JoinColumn(name="crime_id")
    private Crime crime;
}
