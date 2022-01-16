package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.SexType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CHARACTER")
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @Min(1)
    @Max(119)
    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexType sex;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "system_role")
    private SystemRoleType role;

//    @OneToMany(mappedBy = "character") //TODO
//    private Set<Victim> victims;
//
//    @OneToMany(mappedBy = "character") //TODO
//    private Set<Suspect> suspects;
    @ManyToMany //TODO
    @JoinTable(
            name = "LIVING_PLACE",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> livingPlaces;
}
