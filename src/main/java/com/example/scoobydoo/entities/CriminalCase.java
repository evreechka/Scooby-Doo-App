package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Criminal_Case")
public class CriminalCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "case_id")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "punishment")
    private PunishmentType punishment;

    @Min(0)
    @Max(10)
    @Column(name = "severity_case")
    private int severity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monster_id", referencedColumnName = "monster_id")
    private Monster monster;

    @ManyToOne
    @JoinColumn(name="quilt_id")
    private Suspect quilt;

    @ManyToOne
    @JoinColumn(name="crime_id")
    private Crime crime;

    @OneToMany(mappedBy = "criminalCase") //TODO
    private Set<EquipmentCase> equipments;
}
