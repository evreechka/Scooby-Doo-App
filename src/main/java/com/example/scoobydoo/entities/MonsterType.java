package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.MonsterFeatureType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "MONSTER_TYPE")
public class MonsterType implements Serializable {
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
