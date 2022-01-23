package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CRIME_VISIT")
public class CrimeVisit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private long id;

    @Column(name = "visit_num")
    private int visitNumber;

    @Min(0)
    @Max(10)
    @Column(name = "severity_destruction")
    private int severityDestruction;

    @Column(name = "date_visit")
    private LocalDateTime dateVisit;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<Victim> victims;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<Suspect> suspects;

    @OneToMany(mappedBy = "crimeVisit") //TODO
    private Set<VisitParticipant> participants;

    //TODO
    @ManyToOne
    @JoinColumn(name="scene_id", referencedColumnName = "scene_id")
    private CrimeScene crimeScene;
    //TODO
    @ManyToOne
    @JoinColumn(name="crime_id", referencedColumnName = "crime_id")
    private Crime crime;
}
