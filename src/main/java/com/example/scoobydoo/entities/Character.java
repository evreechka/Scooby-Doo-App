package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.SexType;
import com.example.scoobydoo.entities.enums.SystemRoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "character_id")
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @Min(1)
    @Max(120)
    @Column(name = "age")
    private int age;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "sex")
    private SexType sex;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "system_role")
    private SystemRoleType role;
    @ManyToMany
    @JoinTable(
            name = "Living_Place",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> livingPlaces;
}
