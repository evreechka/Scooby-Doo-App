package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.PlaceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Crime_Scene")
public class CrimeScene {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scene_id")
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "place")
    private PlaceType place;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

}
