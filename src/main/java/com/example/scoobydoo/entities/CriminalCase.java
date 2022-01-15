package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.PunishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CRIMINAL_CASE")
public class CriminalCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "punishment")
    private PunishmentType punishment;

    @Min(0)
    @Max(10)
    @Column(name = "severity_case")
    private int severity;

    //TODO
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id", referencedColumnName = "monster_id")
    private Monster monster;

    //TODO
    @ManyToOne
    @JoinColumn(name="quilt_id", referencedColumnName = "suspect_id")
    private Suspect quilt;

    //TODO
    @ManyToOne
    @JoinColumn(name="crime_id", referencedColumnName = "crime_id")
    private Crime crime;

    //TODO
    @OneToMany(mappedBy = "criminalCase") //TODO
    private Set<EquipmentCase> equipments;
}
