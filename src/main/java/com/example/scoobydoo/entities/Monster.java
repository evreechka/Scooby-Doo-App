package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MONSTER")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monster_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Min(0)
    @Column(name = "weight")
    private float weight;

    @Min(0)
    @Column(name = "height")
    private float height;

    @Column(name = "internet_inform")
    private String internetInformation;

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "monster_type_id")
    private MonsterType monsterType;

}
