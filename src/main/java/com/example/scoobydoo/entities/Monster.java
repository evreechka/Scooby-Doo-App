package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "name shouldn't be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weight", columnDefinition = "DOUBLE PRECISION CHECK (weight > 0.0)")
    private float weight;

    @Column(name = "height", columnDefinition = "DOUBLE PRECISION CHECK (height > 0.0)")
    private float height;

    @Column(name = "internet_inform")
    private String internetInformation;

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "monster_type_id")
    private MonsterType monsterType;

}
