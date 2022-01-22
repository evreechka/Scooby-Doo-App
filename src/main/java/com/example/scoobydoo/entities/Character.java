package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.SexType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "name shouldn't be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "surname shouldn't be blank")
    @Column(name = "surname")
    private String surname;

    @Min(value = 1, message = "Age should be > 0")
    @Max(value = 119, message = "Age should be < 120")
    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexType sex;

    @OneToOne(mappedBy = "character")
    private Investigator investigator;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @ManyToMany //TODO
    @JoinTable(
            name = "LIVING_PLACE",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> livingPlaces;
}
