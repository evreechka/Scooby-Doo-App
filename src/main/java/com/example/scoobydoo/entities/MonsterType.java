package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Monster_Type")
public class MonsterType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "monster_type_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "monster_feature")
    @Enumerated
    private MonsterFeatureType monsterFeature;

    @ManyToMany(mappedBy = "monsterTypes")
    private Set<Item> items;
}
