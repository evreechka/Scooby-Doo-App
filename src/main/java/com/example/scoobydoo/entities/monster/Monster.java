package com.example.scoobydoo.entities.monster;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Monster")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "monster_id")
    private long id;

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
    @JoinColumn(name="monster_type_id")
    private MonsterType monsterType;

}
