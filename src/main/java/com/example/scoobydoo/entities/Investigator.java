package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.FeatureType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "INVESTIGATOR")
public class Investigator implements Serializable {
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
