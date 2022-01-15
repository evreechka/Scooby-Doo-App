package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.FeatureType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "INVESTIGATOR")
public class Investigator {
    @Id
    @Column(name = "investigator_id")
    private long investigatorId;

    @OneToOne
    @JoinColumn(name = "investigator_id", referencedColumnName = "character_id")
    private Character character;

    @Column(name = "feature")
    @Enumerated(EnumType.STRING)
    private FeatureType feature;

    @OneToMany(mappedBy = "investigator") //TODO
    private Set<History> histories;

    @OneToMany(mappedBy = "investigator") //TODO
    private Set<VisitParticipant> participants;

    //TODO
    @ManyToMany(mappedBy = "investigators")
    private Set<Crime> crimes;
}
