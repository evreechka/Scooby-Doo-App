package com.example.scoobydoo.entities.characters;

import com.example.scoobydoo.entities.investigation.crime.Crime;
import com.example.scoobydoo.entities.investigation.crime.VisitParticipant;
import com.example.scoobydoo.entities.enums.FeatureType;
import com.example.scoobydoo.entities.storage.History;
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
