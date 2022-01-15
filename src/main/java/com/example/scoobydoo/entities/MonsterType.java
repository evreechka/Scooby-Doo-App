package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.MonsterFeatureType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MONSTER_TYPE")
public class MonsterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monster_type_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "monster_feature")
    @Enumerated(EnumType.STRING)
    private MonsterFeatureType monsterFeature;
    //TODO
    @ManyToMany(mappedBy = "monsterTypes")
    private Set<Item> items;
    //TODO
    @OneToMany(mappedBy = "monsterType")
    private Set<Monster> monsters;
}
