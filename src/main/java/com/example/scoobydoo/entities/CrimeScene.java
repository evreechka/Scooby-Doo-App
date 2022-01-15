package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.PlaceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CRIME_SCENE")
public class CrimeScene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "place")
    private PlaceType place;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
}
