package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "MONSTER")
public class Monster implements Serializable {
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
