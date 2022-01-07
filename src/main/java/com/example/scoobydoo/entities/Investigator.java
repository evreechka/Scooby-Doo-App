package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Investigator")
public class Investigator {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "investigator_id", referencedColumnName = "character_id")
    private Character character;

    @Column(name = "feature")
    @Enumerated(EnumType.ORDINAL)
    private FeatureType feature;

    @OneToMany(mappedBy = "investigator") //TODO
    private Set<History> histories;

    @OneToMany(mappedBy = "investigator") //TODO
    private Set<VisitParticipant> participants;

    @ManyToMany(mappedBy = "investigators")
    private Set<Crime> crimes;
}
